package com.wgluka.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by yukai on 2017/4/17.
 */
public class FactoryLoaderUtil {
    private static final Logger logger = LoggerFactory.getLogger(FactoryLoaderUtil.class);

    public static Enumeration<URL> getResource(){
        return getResource(ClassLoaderUtil.getClassLoader(), "starter/starter.factories");
    }

    public static Enumeration<URL> getResource(ClassLoader classLoader, String path) {
        Enumeration<URL> urls = null;
        try {
            urls = classLoader.getResources(path);
        } catch (IOException e) {
            logger.info("fail to load " + path, e);
            throw new RuntimeException("fail to load " + path, e);
        }
        return urls;
    }
}
