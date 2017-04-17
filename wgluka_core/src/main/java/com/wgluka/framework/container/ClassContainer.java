package com.wgluka.framework.container;

import com.wgluka.framework.constant.DefaultConfiguration;
import com.wgluka.framework.util.ClassLoaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yukai on 2017/4/14.
 */
public class ClassContainer {

    private static final Logger logger = LoggerFactory.getLogger(ClassContainer.class);

//    第一种做法是加载所有的类
//    private static final Set<Class<?>> CLASS_SET = ClassLoaderUtil.getAllClass(ConfigContainer.getAppBasePackage());

    //改成了只加载被声明为Bean的类
    private static final Set<Class<?>> BEAN_CLASS_SET;

    //包含所有的Class
    private static final Set<Class<?>> ALL_CLASS_SET;

    static {
        BEAN_CLASS_SET = new HashSet<>();

        Set<Class<?>> beanAnnotations = ClassLoaderUtil.getAllClass(DefaultConfiguration.defaultBeanAnnotationPackage);
        ALL_CLASS_SET = ClassLoaderUtil.getAllClass(DefaultConfiguration.defaultAppBasePackage);

        if (beanAnnotations.size() != 0 && ALL_CLASS_SET.size() != 0) {
            for (Class clazz : ALL_CLASS_SET) {
                for (Class<?> anno : beanAnnotations) {
                    if (clazz.isAnnotationPresent(anno)) {
                        BEAN_CLASS_SET.add(clazz);
                        break;
                    }
                }
            }
        }
    }

    public static Set<Class<?>> getClassByAnnotation(Class<? extends Annotation> annotationType) {
        return getClassSetByAnnotation(annotationType, BEAN_CLASS_SET);
    }

    public static Set<Class<?>> getAllBeanClass() {
        return BEAN_CLASS_SET;
    }

    public static Set<Class<?>> getAllClass() {
        return ALL_CLASS_SET;
    }

    public static Set<Class<?>> getClassInAllByAnnotation(Class<? extends Annotation> annotation) {
        return getClassSetByAnnotation(annotation, ALL_CLASS_SET);
    }

    public static Set<Class<?>> getClassInBeanByAnnotation(Class<? extends Annotation> annotation) {
        return getClassSetByAnnotation(annotation, BEAN_CLASS_SET);
    }

    public static boolean containInBeanClass(String className) {
        if (className == null || className.length() == 0)
            return false;

        try {
            Class<?> clazz = Class.forName(className);
            return containInBeanClass(clazz);
        } catch (ClassNotFoundException e) {
            logger.info(className + " not found", e);
        }
        return false;
    }

//    public static boolean containMethod(Class<?> clazz, String method){
//        Method[] methods = clazz.get
//    }

    public static boolean containInBeanClass(Class<?> clazz) {
        return BEAN_CLASS_SET.contains(clazz);
    }

    private static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotation, Set<Class<?>> set) {
        if (set.isEmpty())
            return null;

        Set<Class<?>> resultSet = new HashSet<>();
        for (Class<?> clazz : set) {
            if (clazz.isAnnotationPresent(annotation)) {
                resultSet.add(clazz);
            }
        }
        return resultSet;
    }
}
