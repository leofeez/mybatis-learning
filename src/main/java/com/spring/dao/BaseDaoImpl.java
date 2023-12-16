package com.spring.dao;

import com.exception.BaseException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author leofee
 * @date 2019/1/11
 */
public abstract class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

    /**
     * 指定命名空间
     * @return 命名空间
     */
    protected abstract String nameSpace();

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public T findByPrimaryKey(Object id) {
        return getSqlSession().selectOne(nameSpace().concat(".findByPrimaryKey"), id);
    }

    @Override
    public List<T> findRecords(T param) {
        return getSqlSession().selectList(nameSpace().concat(".findRecords"), param);
    }

    @Override
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

    @Override
    public void deleteByPrimaryKey(Object id) {
        getSqlSession().delete(nameSpace().concat(".deleteByPrimaryKey"), id);
    }
}
