package com.wgluka.framework.aop.advisor;

import com.wgluka.framework.aop.advice.Advicer;
import com.wgluka.framework.aop.advice.AfterAdvicer;
import com.wgluka.framework.aop.advice.AroundAdvicer;
import com.wgluka.framework.aop.advice.BeforeAdvicer;
import com.wgluka.framework.aop.annotation.After;
import com.wgluka.framework.aop.annotation.Around;
import com.wgluka.framework.aop.annotation.Aspect;
import com.wgluka.framework.aop.annotation.Before;
import com.wgluka.framework.aop.pointcut.DefaultPointcut;
import com.wgluka.framework.aop.pointcut.Pointcut;
import com.wgluka.framework.aop.proxy.AfterProxyAdapter;
import com.wgluka.framework.aop.proxy.AroundProxyAdapter;
import com.wgluka.framework.aop.proxy.BeforeProxyAdapter;
import com.wgluka.framework.aop.proxy.ProxyAdapter;
import com.wgluka.framework.aop.util.AopClassUtil;
import com.wgluka.framework.container.ClassContainer;
import com.wgluka.framework.util.CollectionUtil;

import java.util.*;

/**
 * Created by yukai on 2017/4/16.
 */
public class AdvisorManager {

    private static Map<Class<?>, List<ProxyAdapter>> adapterMap;

    static {
        createAdapterMap();
    }

    public static void createAdapterMap() {
        adapterMap = new HashMap<>();

        Set<Class<?>> classSet = AopClassUtil.getClassByAnnotation(Aspect.class);
        if (classSet == null || classSet.isEmpty())
            return;

        for (Class<?> clazz : classSet) {

            Pointcut pointcut = null;
            Advicer advicer = null;
            ProxyAdapter proxyAdapter = null;
            String targetClassName = null;

            try {
                if (clazz.isAnnotationPresent(Before.class)) {
                    if (!BeforeAdvicer.class.isAssignableFrom(clazz))
                        continue;

                    Before before = clazz.getAnnotation(Before.class);
                    pointcut = new DefaultPointcut(before.className(), before.methodName());
                    advicer = (BeforeAdvicer) clazz.newInstance();
                    Advisor advisor = new DefaultAdvisor(pointcut, advicer);
                    proxyAdapter = new BeforeProxyAdapter(advisor);
                    targetClassName = before.className();


                } else if (clazz.isAnnotationPresent(After.class)) {
                    if (!AfterAdvicer.class.isAssignableFrom(clazz))
                        continue;

                    After after = clazz.getAnnotation(After.class);
                    pointcut = new DefaultPointcut(after.className(), after.methodName());
                    advicer = (AfterAdvicer) clazz.newInstance();
                    Advisor advisor = new DefaultAdvisor(pointcut, advicer);
                    proxyAdapter = new AfterProxyAdapter(advisor);
                    targetClassName = after.className();

                } else if (clazz.isAnnotationPresent(Around.class)) {
                    if (!AroundAdvicer.class.isAssignableFrom(clazz))
                        continue;

                    Around around = clazz.getAnnotation(Around.class);
                    pointcut = new DefaultPointcut(around.className(), around.methodName());
                    advicer = (AroundAdvicer) clazz.newInstance();
                    Advisor advisor = new DefaultAdvisor(pointcut, advicer);
                    proxyAdapter = new AroundProxyAdapter(advisor);
                    targetClassName = around.className();

                } else {
                    continue;
                }
            } catch (Exception e) {
                throw new RuntimeException("fail to init advisors", e);
            }

            if (!ClassContainer.containInBeanClass(targetClassName))
                continue;

            try {
                Class<?> targetClass = Class.forName(targetClassName);

                List<ProxyAdapter> adapters = adapterMap.get(targetClass);
                if (adapters == null) {
                    adapters = new ArrayList<>();
                }

                adapters.add(proxyAdapter);
                if (adapters.size() == 1)
                    adapterMap.put(targetClass, adapters);

            } catch (ClassNotFoundException e) {
                throw new RuntimeException("fail to create class", e);
            }

        }
    }

    public static boolean isEmpty() {
        return CollectionUtil.isEmpty(adapterMap);
    }

    public static Map<Class<?>, List<ProxyAdapter>> getAdapterMap() {
        return adapterMap;
    }

    public static void clear() {
        adapterMap = null;
    }
}
