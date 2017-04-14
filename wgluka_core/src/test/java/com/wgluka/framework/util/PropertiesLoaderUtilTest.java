package com.wgluka.framework.util;

import com.wgluka.framework.constant.DefaultConfiguration;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by yukai on 2017/4/13.
 */
public class PropertiesLoaderUtilTest {

    /**
     * 测试资源加载工具类中加载资源属性
     */
    @Test
    public void loadProperties(){
        Properties properties = PropertiesLoaderUtil.loadProperties(DefaultConfiguration.propertiesLocation);
        Assert.assertEquals("wgluka", properties.get("name"));
        Assert.assertEquals("guangzhou", properties.getProperty("location"));
    }

    @Test
    public void getString(){
        Properties properties = PropertiesLoaderUtil.loadProperties(DefaultConfiguration.propertiesLocation);
        Assert.assertEquals("wgluka", PropertiesLoaderUtil.getString(properties, "name"));
        Assert.assertEquals("test", PropertiesLoaderUtil.getString(properties, "test", "test"));
    }
}
