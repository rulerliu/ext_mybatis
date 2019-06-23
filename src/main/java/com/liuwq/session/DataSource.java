package com.liuwq.session;

import lombok.Data;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 上午 11:24
 * @version: V1.0
 */
@Data
public class DataSource {

    private String driver;
    private String url;
    private String username;
    private String password;

    public DataSource(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

}
