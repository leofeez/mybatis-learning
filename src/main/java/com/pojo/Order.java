package com.pojo;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class Order {

    private Integer orderId;

    private String orderCode;

    private LocalDateTime createTime;

    private User user;
}
