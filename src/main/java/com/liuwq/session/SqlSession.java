package com.liuwq.session;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 上午 11:39
 * @version: V1.0
 */
public interface SqlSession  {

    <T> T getMapper(Class<T> type);
}
