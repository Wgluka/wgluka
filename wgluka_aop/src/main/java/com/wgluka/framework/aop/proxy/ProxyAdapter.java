package com.wgluka.framework.aop.proxy;

import java.lang.reflect.Method;

/**
 * Created by yukai on 2017/4/16.
 */
public interface ProxyAdapter {
    boolean isMatched(Class<?> clazz, Method method);
    Object process(ProxyChain proxyChain);
}
