package com.wgluka.framework.container;

import com.wgluka.framework.constant.DefaultConfiguration;
import com.wgluka.framework.util.PropertiesLoaderUtil;

import java.util.Properties;

/**
 * Created by yukai on 2017/4/14.
 */
public class ConfigContainer {

    private static final Properties PROPERTIES = PropertiesLoaderUtil.loadProperties(DefaultConfiguration.propertiesLocation);

//    public static Properties getProperties(){
//        return PROPERTIES;
//    }

    public static String getProperty(String key){
        return PropertiesLoaderUtil.getString(PROPERTIES, key);
    }

    public static String getProperty(String key, String defaultValue){
        return PropertiesLoaderUtil.getString(PROPERTIES, key, defaultValue);
    }

    public static String getAppBasePackage(){
        return getProperty(DefaultConfiguration.defaultAppBasePackage, "");
    }

}
