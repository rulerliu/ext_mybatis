package com.liuwq.session;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 上午 11:38
 * @version: V1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return openSessionFromDataSource();
    }

    private SqlSession openSessionFromDataSource() {
        // 只用默认的sqlSqlSession
        return new DefaultSqlSession(configuration);
    }

}
