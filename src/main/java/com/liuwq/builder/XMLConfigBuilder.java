package com.liuwq.builder;

import com.liuwq.exception.BuilderException;
import com.liuwq.session.Configuration;
import com.liuwq.session.DataSource;
import com.liuwq.utils.ClassUtil;
import com.liuwq.utils.PropertiesUtil;

import java.util.List;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 上午 11:06
 * @version: V1.0
 */
public class XMLConfigBuilder extends BaseBuilder {
    /**
     * 配置文件名称
     */
    private String propertiesName;
    private PropertiesUtil propertiesUtil;
    private Boolean parsed = false;

    public XMLConfigBuilder() {}

    public XMLConfigBuilder(String propertiesName) {
        super.configuration = new Configuration();
        this.propertiesName = propertiesName;
        this.propertiesUtil = new PropertiesUtil(propertiesName);
    }

    public Configuration parse() {
        if (parsed) {
            throw new BuilderException("Each XMLConfigBuilder can only be used once.");
        }
        parsed = true;
        parseConfiguration();
        return configuration;
    }

    private void parseConfiguration() {
        try {
            // 1.解析配置文件获取DataSource
            propertieDataSource();
            // 2.解析mappers注册
            propertieMappers();
        } catch (Exception e) {
            throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
    }

    private void propertieDataSource() {
        String driver = propertiesUtil.readProperty("driver");
        String url = propertiesUtil.readProperty("url");
        String username = propertiesUtil.readProperty("username");
        String password = propertiesUtil.readProperty("password");
        DataSource dataSource = new DataSource(driver, url, username, password);
        configuration.setDataSource(dataSource);
    }

    private void propertieMappers() {
        String mappers = propertiesUtil.readProperty("mappers");
        configuration.setMappers(mappers);
        // 使用java的反射机制获取该包下面 所有的 mapper接口
        List<Class<?>> classesByPackageName = ClassUtil.getClassSet(mappers);
        if (classesByPackageName != null && classesByPackageName.size() > 0) {
            for (Class<?> mapperInterface : classesByPackageName) {
                configuration.addMapper(mapperInterface);
            }
        }
    }

}
