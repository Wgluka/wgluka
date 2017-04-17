package com.wgluka.framework.aop.proxy;

import com.wgluka.framework.aop.advisor.AdvisorManager;
import com.wgluka.framework.container.BeanContainer;

import java.util.List;
import java.util.Map;

/**
 * Created by yukai on 2017/4/16.
 */
public class ProxyManager {

    static {
        createProxy();
    }

    public static void createProxy() {
        Map<Class<?>, List<ProxyAdapter>> adpters = AdvisorManager.getAdapterMap();

        for (Map.Entry<Class<?>, List<ProxyAdapter>> entry : adpters.entrySet()) {
            Object proxy = createProxy(entry.getKey(), entry.getValue());
            BeanContainer.setBean(entry.getKey(), proxy);
        }

        AdvisorManager.clear();
    }

    public static  <T> T createProxy(Class<T> tClass, List<ProxyAdapter> proxyList) {

        if (tClass.getInterfaces().length != 0) {
            return JdkDynamicProxy.createProxy(tClass, proxyList);
        } else {
            return CGlibDynamicProxy.createProxy(tClass, proxyList);
        }
    }

}
