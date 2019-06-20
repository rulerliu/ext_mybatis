package com.liuwq.session;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 上午 11:38
 * @version: V1.0
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
