package com.wgluka.framework.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by yukai on 2017/4/14.
 */
public class BeanContainer {
    private static final Logger logger = LoggerFactory.getLogger(BeanContainer.class);

    private static final Map<Class<?>, Object> BEAN_MAP;

    static {
        BEAN_MAP = new HashMap<>();
        Set<Class<?>> classSet = ClassContainer.getAllClass();
        for (Class clazz : classSet) {
            try {
                Object object = clazz.newInstance();
                BEAN_MAP.put(clazz, object);
            } catch (Exception e) {
                logger.info("fail to initial bean container", e);
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> T getBean(Class<T> tClass) {
        Object object = BEAN_MAP.get(tClass);
//        if (object == null)
//            throw new RuntimeException("do not contain bean with type " + tClass);

        return (T) object;
    }

    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    public static int size(){
        return BEAN_MAP.size();
    }
}
