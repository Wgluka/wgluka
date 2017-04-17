package com.wgluka.framework.aop.proxy;

import com.wgluka.framework.aop.advice.BeforeAdvicer;
import com.wgluka.framework.aop.advisor.Advisor;

import java.lang.reflect.Method;

/**
 * Created by yukai on 2017/4/16.
 */
public class BeforeProxyAdapter implements ProxyAdapter {

    private Advisor advisor;

    public BeforeProxyAdapter(Advisor advisor) {
        this.advisor = advisor;
    }

    @Override
    public boolean isMatched(Class<?> clazz, Method method) {
        return advisor.isMathched(clazz, method);
    }

    @Override
    public Object process(ProxyChain proxyChain) {
        BeforeAdvicer advicer = (BeforeAdvicer)advisor.getAdvicer();
        advicer.before();
        return proxyChain.process();
    }
}
