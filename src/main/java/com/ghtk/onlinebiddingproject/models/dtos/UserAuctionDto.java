package com.ghtk.onlinebiddingproject.models.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "UserAuctionDto",
        classes =
        @ConstructorResult(
                targetClass = UserAuctionDto.class,
                columns = {
                        @ColumnResult(name = "username", type = String.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "code", type = String.class),
                }))
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserAuctionDto {

    @Id
    private Integer id;

    private String name;

    private String code;

}
