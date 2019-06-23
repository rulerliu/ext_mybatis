package com.liuwq.executor;

import com.liuwq.session.Configuration;
import com.liuwq.session.DataSource;
import com.liuwq.statement.MappedStatement;
import com.liuwq.utils.MyBatisJDBCUtil;

import java.util.List;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/23 0023 20:49
 * @version: V1.0
 * @Copyright:该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下， 私自分享视频和源码属于违法行为。
 */
public class SimpleExecutor extends BaseExecutor {
    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter) {
        //
        DataSource dataSource = ms.getConfiguration().getDataSource();
        MyBatisJDBCUtil myBatisJDBCUtil = new MyBatisJDBCUtil(dataSource);
        List<E> objects = ( List<E>) myBatisJDBCUtil.queryListExecute(ms.getSqlValue(), ms.getArgs(), ms.getResultType());
        return objects;
    }
}
