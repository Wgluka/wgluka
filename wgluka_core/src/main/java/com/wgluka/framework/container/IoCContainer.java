package com.wgluka.framework.container;

import com.wgluka.framework.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by yukai on 2017/4/14.
 */
public class IoCContainer {
    private static final Logger logger = LoggerFactory.getLogger(IoCContainer.class);

    public static void init() {
        Map<Class<?>, Object> bean_map = BeanContainer.getBeanMap();
        for (Map.Entry<Class<?>, Object> entry : bean_map.entrySet()) {
            Class<?> clazz = entry.getKey();
            Object target = entry.getValue();

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(Autowired.class) != null) {
                    Object injectedObject = bean_map.get(field.getType());
                    if (injectedObject == null) {
                        throw new RuntimeException(field.getName() + " could not be injected into "
                                + clazz.getName());
                    }

                    try {
                        field.setAccessible(true);
                        field.set(target, injectedObject);
                    } catch (IllegalAccessException e) {
                        logger.info("inject fail Object: " + clazz.getName() + " field: " +
                                field.getName(), e);

                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

}
