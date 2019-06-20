package com.liuwq.session;

import com.liuwq.builder.XMLConfigBuilder;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 上午 11:04
 * @version: V1.0
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(String propertiesName) throws Exception {
        try {
            XMLConfigBuilder parser = new XMLConfigBuilder(propertiesName);
            return build(parser.parse());
        } catch (Exception e) {
            throw new Exception("Error building SqlSession.", e);
        }
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
