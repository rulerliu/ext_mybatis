package com.liuwq.session;

import com.liuwq.statement.MappedStatement;

import java.util.List;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 上午 11:39
 * @version: V1.0
 */
public interface SqlSession  {

    <T> T getMapper(Class<T> type);

    <T> T selectOne(MappedStatement ms, Object parameter);

    <E> List<E> selectList(MappedStatement ms, Object parameter);

}
