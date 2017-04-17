package com.wgluka.framework.aop.advisor;

import com.wgluka.framework.aop.advice.Advicer;
import com.wgluka.framework.aop.pointcut.Pointcut;

import java.lang.reflect.Method;

/**
 * Created by yukai on 2017/4/16.
 */
public interface Advisor {
    boolean isMathched(Class<?> clazz, Method method);
    void setPointcut(Pointcut pointcut);
    void setAdvicer(Advicer advicer);
    Advicer getAdvicer();

}
