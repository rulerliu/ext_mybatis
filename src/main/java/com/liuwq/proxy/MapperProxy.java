package com.liuwq.proxy;

import com.liuwq.annotation.Select;
import com.liuwq.session.SqlSession;
import com.liuwq.statement.MappedStatement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler {

    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        if (method.getDeclaredAnnotation(Select.class) != null) {

            MappedStatement.Builder builder = new MappedStatement.Builder(mapperInterface.getName() + "." + method.getName(), method.getDeclaredAnnotation(Select.class), method.getReturnType(), args);
            MappedStatement ms = builder.build();
            return sqlSession.selectOne(ms, args);
        } else {
            throw new Exception("方法上面无注解");
        }
    }
}
