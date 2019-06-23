package com.liuwq.statement;

import com.liuwq.annotation.Select;
import com.liuwq.session.Configuration;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/23 0023 20:13
 * @version: V1.0
 * @Copyright:该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下， 私自分享视频和源码属于违法行为。
 */
public class MappedStatement {
    private Configuration configuration;
    private String id;
    /**
     * sql语句
     */
    private String sqlValue;
    /**
     * sql类型
     */
    private Annotation sqlType;
    /**
     * 返回类型
     */
    private Class<?> resultType;
    /**
     * sal参数
     */
    private Object[] args;

    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(String id, Annotation sqlType, Class<?> resultType, Object[] args) {
            mappedStatement.id = id;
            mappedStatement.sqlType = sqlType;
            mappedStatement.resultType = resultType;
            mappedStatement.args = args;
            getSqlValue(mappedStatement.sqlType);
        }

        public void getSqlValue(Annotation sqlType) {
            if (sqlType instanceof Select) {
                Select select = (Select) sqlType;
                mappedStatement.sqlValue = select.value();
            }
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.sqlValue != null;
            assert mappedStatement.sqlType != null;
            assert mappedStatement.args != null;
            return mappedStatement;
        }
    }

    public String getMappedStatementKey() {
        String mappedStatementKey = sqlValue + arrayToString() + resultType.toString();
        return mappedStatementKey;
    }

    public String arrayToString() {
        String str = "";
        for (int i = 0; i < args.length; i++) {
            Object obj = (Object) args[i];
            str += obj + "";
        }
        return str;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSqlValue() {
        return sqlValue;
    }

    public Annotation getSqlType() {
        return sqlType;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setSqlValue(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    public void setSqlType(Annotation sqlType) {
        this.sqlType = sqlType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Object[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "MappedStatement{" +
                "configuration=" + configuration +
                ", sqlValue='" + sqlValue + '\'' +
                ", sqlType=" + sqlType +
                ", args=" + Arrays.toString(args) +
                ", resultType=" + resultType +
                '}';
    }
}
