package com.liuwq.session;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 上午 11:43
 * @version: V1.0
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.<T>getMapper(type, this);
    }
}
