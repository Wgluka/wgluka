package com.wgluka.framework.aop.advisor;

import com.wgluka.framework.aop.advice.Advicer;
import com.wgluka.framework.aop.pointcut.Pointcut;

import java.lang.reflect.Method;

/**
 * Created by yukai on 2017/4/16.
 */
public class DefaultAdvisor implements Advisor{

    private Pointcut pointcut;
    private Advicer advicer;

    public DefaultAdvisor(Pointcut pointcut, Advicer advicer) {
        this.pointcut = pointcut;
        this.advicer = advicer;
    }

    @Override
    public boolean isMathched(Class<?> clazz, Method method) {
        return pointcut.matches(clazz, method);
    }

    @Override
    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }

    @Override
    public void setAdvicer(Advicer advicer) {
        this.advicer = advicer;
    }

    @Override
    public Advicer getAdvicer() {
        return this.advicer;
    }
}
