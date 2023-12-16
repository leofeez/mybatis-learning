package com.pojo;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class Order {

    private Integer orderId;

    private String orderCode;

    private String orderType;

    private LocalDateTime createTime;

    private Integer userId;

    private User user;
}
