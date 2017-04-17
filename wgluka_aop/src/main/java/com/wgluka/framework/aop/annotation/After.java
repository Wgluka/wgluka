package com.wgluka.framework.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yukai on 2017/4/16.
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface After {
    String className();
    String methodName() default ".*";
}
