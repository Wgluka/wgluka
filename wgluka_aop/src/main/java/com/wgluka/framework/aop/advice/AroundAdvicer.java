package com.wgluka.framework.aop.advice;

/**
 * Created by yukai on 2017/4/16.
 */
public interface AroundAdvicer extends Advicer {
    void before();

    void after(Object result);
}
