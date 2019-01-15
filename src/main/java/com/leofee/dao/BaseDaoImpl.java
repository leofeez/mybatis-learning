package com.leofee.dao;

import com.leofee.exception.BaseException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author leofee
 * @Date: 2019/1/11
 */
@Component
public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

    /**
     * 命名空间
     */
    @Setter(AccessLevel.PROTECTED)
    @Getter(AccessLevel.PROTECTED)
    private String nameSpace;

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public T findByPrimaryKey(Integer id) {
        return getSqlSession().selectOne(nameSpace.concat(".findByPrimaryKey"), id);
    }

    public List<T> findRecords(T param) {
        return getSqlSession().selectList(nameSpace.concat(".findRecords"), param);
    }

    public T loadEntity(T param) {
        List<T> records = findRecords(param);

        if (records == null || records.size() == 0) {
            return null;
        }

        if (records.size() > 1) {
            throw new BaseException(param.getClass().getName() + " 记录重复.");
        }

        return records.get(0);
    }

    public void deleteByPrimaryKey(Integer id) {
        getSqlSession().delete(nameSpace.concat(".deleteByPrimaryKey"), id);
    }
}
