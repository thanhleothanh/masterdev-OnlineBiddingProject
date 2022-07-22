package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import com.ghtk.onlinebiddingproject.constants.ReviewResultConstants;
import com.ghtk.onlinebiddingproject.daos.AuctionDao;
import com.ghtk.onlinebiddingproject.exceptions.BadRequestException;
import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.entities.*;
import com.ghtk.onlinebiddingproject.models.requests.AuctionRequestDto;
import com.ghtk.onlinebiddingproject.models.responses.AuctionPagingResponse;
import com.ghtk.onlinebiddingproject.models.responses.AuctionTopTrendingDto;
import com.ghtk.onlinebiddingproject.repositories.AuctionRepository;
import com.ghtk.onlinebiddingproject.repositories.ReviewResultRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.AuctionService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import com.ghtk.onlinebiddingproject.utils.DtoToEntityUtils;
import com.ghtk.onlinebiddingproject.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private ItemServiceImpl itemService;
    @Autowired
    private AuctionDao auctionDao;
    @Autowired
    private ReviewResultRepository reviewResultRepository;

    @Override
    public AuctionPagingResponse get(Specification<Auction> spec, HttpHeaders headers, Sort sort) {
        if (PaginationUtils.isPaginationRequested(headers)) {
            return helperGet(spec, PaginationUtils.buildPageRequest(headers, sort));
        } else {
            List<Auction> productEntities = helperGet(spec, sort);
            return new AuctionPagingResponse(productEntities.size(), 0, 0, 0, productEntities);
        }
    }

    @Override
    public List<AuctionTopTrendingDto> getTopTrending() {
        return auctionDao.getTopTrending();
    }

    @Override
    public List<Auction> getMyAuctions(AuctionStatusConstants status) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        if (status != null)
            return auctionRepository.findByUser_IdAndStatus(userDetails.getId(), status, Sort.by(Sort.Direction.DESC, "createdAt"));
        return auctionRepository.findByUser_Id(userDetails.getId(), Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public List<Auction> getAuctionsByUserId(Integer userId) {
        return auctionRepository.findByUser_IdAndStatusNotIn(userId, List.of(AuctionStatusConstants.DRAFT, AuctionStatusConstants.PENDING), Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public Auction getById(Integer id) {
        Auction auction = auctionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy auction với id này!"));
        boolean isPostedByCurrentUser = CurrentUserUtils.isPostedByCurrentUser(auction.getUser().getId());

        if (((auction.getStatus().equals(AuctionStatusConstants.PENDING) || auction.getStatus().equals(AuctionStatusConstants.DRAFT)) && isPostedByCurrentUser) || auction.getStatus().equals(AuctionStatusConstants.OPENING))
            return auction;
        else throw new AccessDeniedException("Không thể lấy thông tin của bài đấu giá vào lúc này!");
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public Auction save(AuctionRequestDto auctionDto, Auction auction, Item item) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        if (userDetails.isSuspended()) throw new AccessDeniedException("Tài khoản của bạn đang bị giới hạn!");
        User user = new User(userDetails.getId());
        auction.setUser(user);

        if (auctionDto.getTimeEnd().isBefore(auctionDto.getTimeStart()))
            throw new BadRequestException("Thời gian bắt đầu và kết thúc đấu giá không hợp lệ!");
        if (LocalDateTime.now().isAfter(auctionDto.getTimeStart()))
            throw new BadRequestException("Thời gian bắt đầu đấu giá không hợp lệ!");
        if (LocalDateTime.now().isAfter(auctionDto.getTimeEnd()))
            throw new BadRequestException("Thời gian kết thúc đấu giá không hợp lệ!");
        if (ChronoUnit.MINUTES.between(auctionDto.getTimeStart(), auctionDto.getTimeEnd()) > 2881)
            throw new BadRequestException("Thời gian bắt đầu và thời gian kết thúc không được cách nhau quá 48 tiếng!");

        auction.setHighestPrice(0.0);
        Auction newAuction = auctionRepository.save(auction);
        item.setAuction(newAuction);
        Item newItem = itemService.save(item);
        newAuction.setItem(newItem);
        return newAuction;
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public Auction put(AuctionRequestDto auctionDto, Auction auction) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        if (userDetails.isSuspended()) throw new AccessDeniedException("Tài khoản của bạn đang bị giới hạn!");

        boolean isPostedByCurrentUser = CurrentUserUtils.isPostedByCurrentUser(auction.getUser().getId());
        AuctionStatusConstants currentStatus = auction.getStatus();
        AuctionStatusConstants newStatus = auctionDto.getStatus();

        LocalDateTime newTimeStart = auctionDto.getTimeStart() != null ? auctionDto.getTimeStart() : auction.getTimeStart();
        LocalDateTime newTimeEnd = auctionDto.getTimeEnd() != null ? auctionDto.getTimeEnd() : auction.getTimeEnd();

        if (!isPostedByCurrentUser)
            throw new AccessDeniedException("Chỉ admin và chủ bài đấu giá mới có quyền sửa!");
        if (!currentStatus.equals(AuctionStatusConstants.DRAFT) && !currentStatus.equals(AuctionStatusConstants.PENDING))
            throw new AccessDeniedException("Không thể thực hiện sửa bài đấu giá khi đã và đang (chờ) đấu giá!");
        if (newStatus != null)
            throw new BadRequestException("Không thể tự ý thay đổi trạng thái bài đấu giá!");
        if (newTimeEnd.isBefore(newTimeStart))
            throw new BadRequestException("Thời gian bắt đầu và kết thúc đấu giá không hợp lệ!");
        if (LocalDateTime.now().isAfter(newTimeStart))
            throw new BadRequestException("Thời gian bắt đầu đấu giá không hợp lệ!");
        if (LocalDateTime.now().isAfter(newTimeEnd))
            throw new BadRequestException("Thời gian kết thúc đấu giá không hợp lệ!");
        if (ChronoUnit.MINUTES.between(newTimeStart, newTimeEnd) > 2881)
            throw new BadRequestException("Thời gian bắt đầu và thời gian kết thúc không được cách nhau quá 48 tiếng!");

        DtoToEntityUtils.copyNonNullProperties(auctionDto, auction);
        if (auctionDto.getCategory() != null) auction.setCategory(new Category(auctionDto.getCategory().getId()));
        return auctionRepository.save(auction);
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public Auction submitPending(Auction auction) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        if (userDetails.isSuspended()) throw new AccessDeniedException("Tài khoản của bạn đang bị giới hạn!");

        boolean isPostedByCurrentUser = CurrentUserUtils.isPostedByCurrentUser(auction.getUser().getId());
        AuctionStatusConstants currentStatus = auction.getStatus();

        if (!isPostedByCurrentUser)
            throw new AccessDeniedException("Chỉ admin và chủ bài đấu giá mới có quyền sửa!");
        if (!currentStatus.equals(AuctionStatusConstants.DRAFT))
            throw new AccessDeniedException("Không thể thực hiện sửa bài đấu giá khi đã và đang đấu giá!");
        if (LocalDateTime.now().isAfter(auction.getTimeEnd()) || LocalDateTime.now().isAfter(auction.getTimeStart()))
            throw new BadRequestException("Thời gian bắt đầu hoặc thời gian kết thúc của bài đấu giá không hợp lệ!");
        if (auction.getItem() != null) {
            auction.setStatus(AuctionStatusConstants.PENDING);
            return auctionRepository.save(auction);
        } else throw new BadRequestException("Không thể submit bài đấu giá khi chưa có sản phẩm!");
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void deleteById(Integer id) {
        Auction auction = getById(id);
        boolean isPostedByCurrentUser = CurrentUserUtils.isPostedByCurrentUser(auction.getUser().getId());
        AuctionStatusConstants currentStatus = auction.getStatus();

        if (!isPostedByCurrentUser)
            throw new AccessDeniedException("Chỉ admin và chủ bài đấu giá mới có quyền sửa!");
        if (currentStatus.equals(AuctionStatusConstants.PENDING) || currentStatus.equals(AuctionStatusConstants.DRAFT))
            auctionRepository.delete(auction);
        else throw new AccessDeniedException("Không thể thực hiện xoá bài đấu giá khi đã và đang đấu giá!");
    }

    /**
     * For admin
     */
    @Override
    public Auction adminGetById(Integer id) {
        return auctionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy auction với id này!"));
    }

    @Override
    public Auction adminPut(AuctionRequestDto auctionDto, Auction auction) {
        DtoToEntityUtils.copyNonNullProperties(auctionDto, auction);
        return auctionRepository.save(auction);
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public Auction adminReviewSubmit(AuctionRequestDto auctionRequestDto, Auction auction) {
        AuctionStatusConstants currentStatus = auction.getStatus();
        if (currentStatus.equals(AuctionStatusConstants.PENDING)) {
            UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
            Admin admin = new Admin(userDetails.getId());

            ReviewResult reviewResult = new ReviewResult(ReviewResultConstants.ACCEPTED, auction, admin);
            reviewResultRepository.save(reviewResult);
            auction.setStatus(AuctionStatusConstants.QUEUED);
            return auctionRepository.save(auction);

//            if (newStatus != null && newStatus.equals(AuctionStatusConstants.QUEUED)) {
//                ReviewResult reviewResult = new ReviewResult(ReviewResultConstants.ACCEPTED, auction, admin);
//                reviewResultRepository.save(reviewResult);
//                auction.setStatus(AuctionStatusConstants.QUEUED);
//                return auctionRepository.save(auction);
//            }
//            if (newStatus != null && newStatus.equals(AuctionStatusConstants.CANCELED)) {
//                ReviewResult reviewResult = new ReviewResult(ReviewResultConstants.REJECTED, auction, admin);
//                reviewResultRepository.save(reviewResult);
//                auction.setStatus(AuctionStatusConstants.CANCELED);
//                return auctionRepository.save(auction);
//            }
        }
        throw new BadRequestException("Chưa thể duyệt bài đấu giá này vào lúc này!");
    }

    @Override
    public void adminDeleteById(Integer id) {
        Auction auction = getById(id);
        auctionRepository.delete(auction);
    }

    /**
     * helper methods
     */
    public List<Auction> helperGet(Specification<Auction> spec, Sort sort) {
        return auctionRepository.findAll(spec, sort);
    }

    public AuctionPagingResponse helperGet(Specification<Auction> spec, Pageable pageable) {
        Page<Auction> page = auctionRepository.findAll(spec, pageable);
        List<Auction> productEntities = page.getContent();
        return new AuctionPagingResponse((int) page.getTotalElements(), page.getNumber(), page.getNumberOfElements(), page.getTotalPages(), productEntities);
    }
}
