package com.liuwq.session;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 上午 11:24
 * @version: V1.0
 */
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

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
