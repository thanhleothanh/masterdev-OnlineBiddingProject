package com.ghtk.onlinebiddingproject.controllers;

import com.ghtk.onlinebiddingproject.models.dtos.ItemDto;
import com.ghtk.onlinebiddingproject.models.dtos.ItemImageDto;
import com.ghtk.onlinebiddingproject.models.entities.Item;
import com.ghtk.onlinebiddingproject.models.entities.ItemImage;
import com.ghtk.onlinebiddingproject.models.requests.ItemRequestDto;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.services.impl.ItemServiceImpl;
import com.ghtk.onlinebiddingproject.utils.converters.DtoToEntityConverter;
import com.ghtk.onlinebiddingproject.utils.converters.EntityToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/items")
public class ItemController {

    @Autowired
    private ItemServiceImpl itemService;
    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;
    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    @PostMapping("")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> save(@Validated @RequestBody ItemRequestDto itemDto) {
        Item item = dtoToEntityConverter.convertToEntity(itemDto);

        ItemDto dtoResponse = entityToDtoConverter.convertToDto(itemService.save(item));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/itemImages")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> postItemImage(@PathVariable Integer id, @Validated @RequestBody ItemImageDto itemImageDto) {
        ItemImage itemImage = dtoToEntityConverter.convertToEntity(itemImageDto);

        ItemImageDto dtoResponse = entityToDtoConverter.convertToDto(itemService.saveItemImage(id, itemImage));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
