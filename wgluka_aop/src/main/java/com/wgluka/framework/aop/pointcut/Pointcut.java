package com.wgluka.framework.aop.pointcut;

import java.lang.reflect.Method;

/**
 * Created by yukai on 2017/4/16.
 */
public interface Pointcut {
    public boolean matches(Class<?> clazz, Method method);
}
