package com.ghtk.onlinebiddingproject.jobs;

import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.repositories.AuctionRepository;
import com.ghtk.onlinebiddingproject.services.impl.NotificationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EndAuctionJob extends QuartzJobBean {
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private NotificationServiceImpl notificationService;

    @Override
    protected void executeInternal(@NotNull JobExecutionContext context) throws JobExecutionException {
        try {
            JobDataMap jobDataMap = context.getMergedJobDataMap();
            Integer auctionId = jobDataMap.getInt("auctionId");
            Auction auction = auctionRepository.findById(auctionId)
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy auction với id này!"));

            if (auction.getHighestPrice() == 0)
                auctionRepository.cancelAuction(auctionId);
            else {
                auctionRepository.endAuction(auctionId);
                auctionRepository.insertWinner(auctionId);
                notificationService.createEndAuctionNotification(auction);
            }
            log.info("ended auction with id " + auctionId);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
