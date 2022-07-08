package com.ghtk.onlinebiddingproject.utils.converters;

import com.ghtk.onlinebiddingproject.models.dtos.AuctionDto;
import com.ghtk.onlinebiddingproject.models.dtos.ItemDto;
import com.ghtk.onlinebiddingproject.models.dtos.ProfileDto;
import com.ghtk.onlinebiddingproject.models.dtos.UserDto;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.Item;
import com.ghtk.onlinebiddingproject.models.entities.Profile;
import com.ghtk.onlinebiddingproject.models.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityToDtoConverter {
    @Autowired
    private ModelMapper modelMapper;

    public AuctionDto convertToDto(Auction auction) {
        return modelMapper.map(auction, AuctionDto.class);
    }

    public ItemDto convertToDto(Item item) {

        return modelMapper.map(item, ItemDto.class);
    }

    public UserDto convertToDto(User user) {

        return modelMapper.map(user, UserDto.class);
    }

    public ProfileDto convertDto(Profile profile){
        return modelMapper.map(profile, ProfileDto.class);
    }

    public List<AuctionDto> convertToListDto(List<Auction> auctions) {
        List<AuctionDto> listDto = new ArrayList<>();
        for (Auction auction : auctions) {
            listDto.add(modelMapper.map(auction, AuctionDto.class));
        }
        return listDto;
    }

//
//    public CategoryDto convertToDto(CategoryEntity categoryEntity) {
//        return modelMapper.map(categoryEntity, CategoryDto.class);
//    }
//
//    public List<CategoryDto> convertToListCategoryDto(List<CategoryEntity> categoryEntity) {
//        List<CategoryDto> listCategoryDto = new ArrayList<>();
//        for (CategoryEntity category : categoryEntity) {
//            listCategoryDto.add(modelMapper.map(category, CategoryDto.class));
//        }
//        return listCategoryDto;
//    }
//
//    public WarehouseDto convertToDto(WarehouseEntity warehouseEntity) {
//        return modelMapper.map(warehouseEntity, WarehouseDto.class);
//    }
//
//    public List<WarehouseDto> convertToListWarehouseDto(List<WarehouseEntity> warehouseEntity) {
//        List<WarehouseDto> listWarehouseDto = new ArrayList<>();
//        for (WarehouseEntity warehouse : warehouseEntity) {
//            listWarehouseDto.add(modelMapper.map(warehouse, WarehouseDto.class));
//        }
//        return listWarehouseDto;
//    }
//
//    public ProvinceDto convertToDto(ProvinceEntity provinceEntity) {
//        return modelMapper.map(provinceEntity, ProvinceDto.class);
//    }
//
//    public List<ProvinceDto> convertToListProvinceDto(List<ProvinceEntity> provinceEntity) {
//        List<ProvinceDto> listProvinceDto = new ArrayList<>();
//        for (ProvinceEntity province : provinceEntity) {
//            listProvinceDto.add(modelMapper.map(province, ProvinceDto.class));
//        }
//        return listProvinceDto;
//    }
//
//    public DistrictDto convertToDto(DistrictEntity districtEntity) {
//        return modelMapper.map(districtEntity, DistrictDto.class);
//    }
//
//    public List<DistrictDto> convertToListDistrictDto(List<DistrictEntity> districtEntity) {
//        List<DistrictDto> listDistrictDto = new ArrayList<>();
//        for (DistrictEntity district : districtEntity) {
//            listDistrictDto.add(modelMapper.map(district, DistrictDto.class));
//        }
//        return listDistrictDto;
//    }
}
