package com.spring.dao;

import com.exception.BaseException;

import java.util.List;

/**
 * @author leofee
 * @Date: 2019/1/11
 */
public interface BaseDao<T> {

    /**
     * 根据逻辑主键查询
     *
     * @param id 主键
     * @return record
     */
    T findByPrimaryKey(Integer id);

    /**
     * 逻辑主键查询
     *
     * @param param 查询参数
     * @exception BaseException 如果查询到多条
     * @return record
     */
    T loadEntity(T param);

    /**
     * 根据参数查询
     *
     * @param param 查询参数
     * @return records
     */
    List<T> findRecords(T param);

    /**
     * 根据主键删除
     *
     * @param id 主键ID
     */
    void deleteByPrimaryKey(Integer id);
}
