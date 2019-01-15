package com.leofee.dao;

import com.leofee.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 * @Date: 2019/1/14
 */
@Component
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {


    public UserDaoImpl(){
        super.setNameSpace("com.leofee.pojo.User");
    }

}
