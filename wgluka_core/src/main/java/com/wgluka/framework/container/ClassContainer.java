package com.wgluka.framework.container;

import com.wgluka.framework.constant.DefaultConfiguration;
import com.wgluka.framework.util.ClassLoaderUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yukai on 2017/4/14.
 */
public class ClassContainer {

//    第一种做法是加载所有的类
//    private static final Set<Class<?>> CLASS_SET = ClassLoaderUtil.getAllClass(ConfigContainer.getAppBasePackage());

    //改成了只加载被声明为Bea的类
    private static final Set<Class<?>> CLASS_SET;

    static {
        CLASS_SET = new HashSet<>();

        Set<Class<?>> beanAnnotations = ClassLoaderUtil.getAllClass(DefaultConfiguration.defaultBeanAnnotationPackage);
        Set<Class<?>> classSet = ClassLoaderUtil.getAllClass(DefaultConfiguration.defaultAppBasePackage);

        if (beanAnnotations.size() != 0 && classSet.size() != 0) {
            for (Class clazz : classSet) {
                for (Class<?> anno: beanAnnotations){
                    if (clazz.isAnnotationPresent(anno)){
                        CLASS_SET.add(clazz);
                        break;
                    }
                }
            }
        }
    }

    public static Set<Class<?>> getClassByAnnotation(Class annotationType) {
        Set<Class<?>> set = new HashSet<>();
        for (Class clazz : CLASS_SET) {
            if (clazz.isAnnotationPresent(annotationType)) {
                set.add(clazz);
            }
        }
        return set;
    }

    public static Set<Class<?>> getAllClass() {
        return CLASS_SET;
    }
}
