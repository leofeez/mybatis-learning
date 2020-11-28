package com.spring.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * order
 * @author 
 */
@Data
public class Order implements Serializable {
    private Integer orderId;

    private String orderCode;

    private Date createTime;

    private Integer userId;

    private String orderType;

    private static final long serialVersionUID = 1L;
}