package com.leofee.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leofee
 * @Date: 2019/1/10
 */
@Component
public class MyBatisUtil {

    @Autowired
    private SqlSessionFactoryBean sqlSessionFactory;

    /**
     * open session
     *
     * @return sql session
     */
    public static SqlSession openSession(){
        SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
        return sqlSessionFactory.openSession(true);
    }

    /**
     * initialize sql session factory
     *
     * @return sql session factory
     */
    public static SqlSessionFactory initSqlSessionFactory(){
       return new SqlSessionFactoryBuilder().build(MyBatisUtil.class.getClassLoader().getResourceAsStream("mybatis.xml")) ;
    }
}
