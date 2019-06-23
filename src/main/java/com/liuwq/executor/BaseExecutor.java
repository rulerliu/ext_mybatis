package com.liuwq.executor;

import com.liuwq.statement.MappedStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/23 0023 20:37
 * @version: V1.0
 * @Copyright:该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下， 私自分享视频和源码属于违法行为。
 */
public abstract class BaseExecutor {
    private Map<Object, Object> localCache = new HashMap<Object, Object>();

    public <E> List<E> query(MappedStatement ms, Object parameter) {
        // 1.先查询一级缓存
        String mappedStatementKey = ms.getMappedStatementKey();
        System.out.println("mappedStatementKey:" + mappedStatementKey);
        List<E> list = (List<E>) localCache.get(mappedStatementKey);
        if (list != null) {
            return list;
        }
        // 2.一级缓存没有，查询数据库
        return queryFromDatabase(ms, parameter);
    }

    private <E> List<E> queryFromDatabase(MappedStatement ms, Object parameter) {
        System.out.println(">>>查询sql：" + ms.getSqlValue());
        List<E> list = doQuery(ms, parameter);
        localCache.put(ms.getMappedStatementKey(), list);
        return list;
    }

    protected abstract <E> List<E> doQuery(MappedStatement ms, Object parameter);

}
