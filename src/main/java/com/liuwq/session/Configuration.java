package com.liuwq.session;

import com.liuwq.exception.BindingException;
import com.liuwq.executor.BaseExecutor;
import com.liuwq.executor.SimpleExecutor;
import com.liuwq.proxy.MapperProxyFactory;
import com.liuwq.statement.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 上午 11:07
 * @version: V1.0
 */
public class Configuration {

    private DataSource dataSource;
    private String mappers;
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<Class<?>, MapperProxyFactory<?>>();
    private final Map<String, MappedStatement> mappedStatements = new HashMap<>();
    private Boolean cacheEnabled = true;

    public BaseExecutor newExecutor() {
        BaseExecutor executor = new SimpleExecutor(this);
//        if (cacheEnabled) {
//            executor = new CachingExecutor(executor);
//        }
        return executor;
    }

    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
            }
            knownMappers.put(type, new MapperProxyFactory<T>(type));
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new BindingException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getMappers() {
        return mappers;
    }

    public void setMappers(String mappers) {
        this.mappers = mappers;
    }
}
