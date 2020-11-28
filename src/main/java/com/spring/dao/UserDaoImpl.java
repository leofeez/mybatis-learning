package com.spring.dao;

import com.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 * @Date: 2019/1/14
 */
@Component
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    protected String nameSpace() {
        return "com.leofee.pojo.User";
    }

}
