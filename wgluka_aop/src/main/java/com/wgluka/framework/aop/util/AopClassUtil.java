package com.wgluka.framework.aop.util;

import com.wgluka.framework.container.ClassContainer;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by yukai on 2017/4/16.
 */
public class AopClassUtil {
    public static Set<Class<?>> getClassByAnnotation(Class<? extends Annotation> annotation){
        return ClassContainer.getClassInAllByAnnotation(annotation);
    }
}
