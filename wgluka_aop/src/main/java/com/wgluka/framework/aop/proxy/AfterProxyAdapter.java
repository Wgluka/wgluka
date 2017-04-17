package com.wgluka.framework.aop.proxy;

import com.wgluka.framework.aop.advice.AfterAdvicer;
import com.wgluka.framework.aop.advisor.Advisor;

import java.lang.reflect.Method;

/**
 * Created by yukai on 2017/4/16.
 */
public class AfterProxyAdapter implements ProxyAdapter {

    private Advisor advisor;

    public AfterProxyAdapter(Advisor advisor) {
        this.advisor = advisor;
    }

    @Override
    public boolean isMatched(Class<?> clazz, Method method) {
        return advisor.isMathched(clazz, method);
    }

    @Override
    public Object process(ProxyChain proxyChain) {
        Object result = proxyChain.process();
        AfterAdvicer advicer = (AfterAdvicer) advisor.getAdvicer();
        advicer.after();
        return result;
    }
}
