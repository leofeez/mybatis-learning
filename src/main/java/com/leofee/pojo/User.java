package com.leofee.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author leofee
 * @Date: 2019/1/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class User extends AbstractEntity {

    private Integer id;

    private String name;
}
