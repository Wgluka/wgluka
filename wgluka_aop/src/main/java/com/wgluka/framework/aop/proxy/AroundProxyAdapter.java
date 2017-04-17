package com.wgluka.framework.aop.proxy;

import com.wgluka.framework.aop.advice.AroundAdvicer;
import com.wgluka.framework.aop.advisor.Advisor;

import java.lang.reflect.Method;

/**
 * Created by yukai on 2017/4/16.
 */
public class AroundProxyAdapter implements ProxyAdapter {

    private Advisor advisor;

    public AroundProxyAdapter(Advisor advisor) {
        this.advisor = advisor;
    }

    @Override
    public boolean isMatched(Class<?> clazz, Method method) {
        return advisor.isMathched(clazz, method);
    }

    @Override
    public Object process(ProxyChain proxyChain) {
        AroundAdvicer advicer = (AroundAdvicer) advisor.getAdvicer();
        advicer.before();
        Object result = proxyChain.process();
        advicer.after(result);
        return result;
    }
}
