package com.ghtk.onlinebiddingproject.models.responses;


import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@SqlResultSetMapping(
        name = "AuctionTopTrendingDto",
        classes =
        @ConstructorResult(
                targetClass = AuctionTopTrendingDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "user_id", type = Integer.class),
                        @ColumnResult(name = "category_id", type = Integer.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "status", type = Integer.class),
                        @ColumnResult(name = "time_start", type = LocalDateTime.class),
                        @ColumnResult(name = "time_end", type = LocalDateTime.class),
                        @ColumnResult(name = "price_start", type = Double.class),
                        @ColumnResult(name = "price_step", type = Double.class),
                        @ColumnResult(name = "highest_price", type = Double.class),
                        @ColumnResult(name = "itemId", type = Integer.class),
                        @ColumnResult(name = "itemName", type = String.class),
                        @ColumnResult(name = "itemDescription", type = String.class),
                        @ColumnResult(name = "itemAmount", type = Integer.class),
                        @ColumnResult(name = "numberOfBids", type = Integer.class),
                }))
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionTopTrendingDto {
    @Id
    private Integer id;
    private Integer user_id;
    private Integer category_id;
    private String description;
    private AuctionStatusConstants status;
    private LocalDateTime time_start;
    private LocalDateTime time_end;
    private Double price_start;
    private Double price_step;
    private Double highest_price;
    private Integer itemId;
    private String itemName;
    private String itemDescription;
    private Integer itemAmount;
    private Integer numberOfBids;

    public AuctionTopTrendingDto(Integer id, Integer user_id, Integer category_id, String description, Integer status, LocalDateTime time_start, LocalDateTime time_end, Double price_start, Double price_step, Double highest_price, Integer itemId, String itemName, String itemDescription, Integer itemAmount, Integer numberOfBids) {
        this.id = id;
        this.user_id = user_id;
        this.category_id = category_id;
        this.description = description;
        this.status = AuctionStatusConstants.fromOrinal(status);
        this.time_start = time_start;
        this.time_end = time_end;
        this.price_start = price_start;
        this.price_step = price_step;
        this.highest_price = highest_price;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemAmount = itemAmount;
        this.numberOfBids = numberOfBids;
    }
}
