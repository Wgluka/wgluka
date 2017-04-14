package com.wgluka.framework.util;

import com.wgluka.framework.constant.DefaultConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Created by yukai on 2017/4/13.
 * <p>
 * PropertiesLoaderUtil
 * 功能：用于提供加载资源配置文件
 */
public class PropertiesLoaderUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoaderUtil.class);

    /**
     * 加载配置文件
     *
     * @param fileName 配置属性文件名(eg. wgluka.properties);
     * @return
     */
    public static Properties loadProperties(String fileName) {
        Properties properties = null;

        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(fileName)) {


            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            logger.info(DefaultConfiguration.propertiesLocation + " could not be found");
            throw new RuntimeException(DefaultConfiguration.propertiesLocation + " could not be found", e);
        }

        logger.info("Property file " + fileName + "had loaded!");
        return properties;
    }

    /**
     * 用于获取配置属性值，默认值为""
     *
     * @param properties
     * @param key
     * @return
     */
    public static String getString(Properties properties, String key) {
        return getString(properties, key, "");
    }

    /**
     * 用于获取配置属性值，含默认值
     *
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties properties, String key, String defaultValue) {
        if (properties == null)
            return defaultValue;

        if (properties.contains(key))
            return properties.getProperty(key);
        return defaultValue;
    }

}
