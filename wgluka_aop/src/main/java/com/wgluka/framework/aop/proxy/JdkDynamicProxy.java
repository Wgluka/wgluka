package com.wgluka.framework.aop.proxy;

import com.wgluka.framework.util.ClassLoaderUtil;
import com.wgluka.framework.util.CollectionUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by yukai on 2017/4/16.
 */
public class JdkDynamicProxy implements InvocationHandler {

    private Object target;
    private List<ProxyAdapter> proxyAdapterList;

    public JdkDynamicProxy(Object target, List<ProxyAdapter> proxyList) {
        this.target = target;
        this.proxyAdapterList = proxyList;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;

        if (CollectionUtil.isEmpty(proxyAdapterList))
            result = method.invoke(target, args);
        else {
            ProxyChain proxyChain = new ProxyChain(target, method, args, proxyAdapterList);
            result = proxyChain.process();
        }
        return result;
    }

    public static <T> T createProxy(Class<T> tClass, List<ProxyAdapter> proxyList) {
        T target = null;
        try {
            target = tClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("fail to create proxy object", e);
        }
        JdkDynamicProxy jdkDynamicProxy = new JdkDynamicProxy(target, proxyList);
        return (T) Proxy.newProxyInstance(ClassLoaderUtil.getClassLoader(), tClass.getInterfaces(), jdkDynamicProxy);
    }
}
