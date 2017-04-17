package com.wgluka.framework.aop.proxy;

import com.wgluka.framework.util.CollectionUtil;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by yukai on 2017/4/16.
 */
public class ProxyChain {
    private Object target;
    private Object[] args;
    private List<ProxyAdapter> proxyAdapters;
    private Method method;
    private MethodProxy methodProxy;
    private Class<?> targetClass;
    private ThreadLocal<Integer> index = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return -1;
        }
    };

    public ProxyChain() {
    }

    public ProxyChain(Object proxy, MethodProxy methodProxy, Object[] args, List<ProxyAdapter> chains,
                      Class<?> clazz, Method method) {
        this(proxy, method, args, chains);
        this.methodProxy = methodProxy;
        this.targetClass = clazz;
    }

    public ProxyChain(Object proxy, Method method, Object[] args, List<ProxyAdapter> chains) {
        this.target = proxy;
        this.method = method;
        this.args = args;
        this.proxyAdapters = chains;
        this.targetClass = proxy.getClass();
    }

    public void increase() {
        index.set(index.get() + 1);
    }

    public void reset() {
        index.set(-1);
    }

    public Object process() {
        Object result = null;
        try {
            if (!CollectionUtil.isEmpty(proxyAdapters) && index.get() < proxyAdapters.size()) {
                increase();
                while (index.get() < proxyAdapters.size() &&
                        !proxyAdapters.get(index.get()).isMatched(targetClass, method))
                    increase();

                if (index.get() == proxyAdapters.size())
                    result = doProxy();
                else {
                    result = proxyAdapters.get(index.get()).process(this);
                }
            } else {
                result = doProxy();
            }
        } catch (Throwable throwable) {
            throw new RuntimeException("fail to do aop action", throwable);
        } finally {
            reset();
        }

        return result;
    }

    public Object doProxy() throws Throwable {
        Object result = null;
        if (methodProxy != null) {
            result = methodProxy.invokeSuper(target, args);
        } else {
            result = method.invoke(target, args);
        }

        return result;
    }
}
