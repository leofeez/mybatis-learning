package com.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * t_role
 * @author 
 */
@Data
public class Role implements Serializable {
    private Integer roleId;

    private String roleName;

    private LocalDateTime createTime;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    private static final long serialVersionUID = 1L;
}