package com.liuwq.session;

import com.liuwq.exception.TooManyResultsException;
import com.liuwq.executor.BaseExecutor;
import com.liuwq.statement.MappedStatement;

import java.util.List;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 上午 11:43
 * @version: V1.0
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private BaseExecutor executor;

    public DefaultSqlSession(Configuration configuration, BaseExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.<T>getMapper(type, this);
    }

    @Override
    public <T> T selectOne(MappedStatement ms, Object parameter) {
        List<T> list = this.<T>selectList(ms, parameter);
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new TooManyResultsException("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        } else {
            return null;
        }
    }

    @Override
    public <E> List<E> selectList(MappedStatement ms, Object parameter) {
        ms.setConfiguration(configuration);
        return executor.query(ms, parameter);
    }
}
