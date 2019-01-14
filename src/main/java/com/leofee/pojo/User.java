package com.leofee.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author leofee
 * @Date: 2019/1/9
 */
@Data
@ToString
public class User implements Serializable {

    private Integer id;

    private String name;

    private String aliveFlag;
}
