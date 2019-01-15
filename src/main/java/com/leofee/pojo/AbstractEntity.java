package com.leofee.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author leofee
 * @Date: 2019/1/15
 */
@Data
public class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**当前有效状态*/
    private String aliveFlag;

    /**语言类型*/
    private String langVer;

    /**创建者*/
    private Integer createUser;

    /**创建时间*/
    private java.util.Date createDate;

    /**修改者*/
    private Integer updateUser;

    /**修改时间*/
    private java.util.Date updateDate;

    /**删除者*/
    private Integer deleteUser;

    /**删除时间*/
    private java.util.Date deleteDate;

}
