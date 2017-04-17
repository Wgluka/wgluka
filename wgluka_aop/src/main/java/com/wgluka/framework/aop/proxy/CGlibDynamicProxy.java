package com.wgluka.framework.aop.proxy;

import com.wgluka.framework.util.CollectionUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by yukai on 2017/4/16.
 */
public class CGlibDynamicProxy implements MethodInterceptor {

    private List<ProxyAdapter> proxyList;
    private Class<?> aClass;

    public CGlibDynamicProxy(List<ProxyAdapter> proxyList, Class<?> clazz) {
        this.proxyList = proxyList;
        this.aClass = clazz;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        Object result = null;
        if (CollectionUtil.isEmpty(proxyList))
            result = methodProxy.invokeSuper(o, objects);
        else {
            ProxyChain proxyChain = new ProxyChain(o, methodProxy, objects, proxyList, aClass, method);
            result = proxyChain.process();
        }

        return result;
    }

    public static <T> T createProxy(Class<?> tClass, List<ProxyAdapter> proxyList) {
        CGlibDynamicProxy proxy = new CGlibDynamicProxy(proxyList, tClass);
        return (T) Enhancer.create(tClass, proxy);
    }

}
