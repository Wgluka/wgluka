package com.wgluka.framework;

import com.wgluka.framework.constant.DefaultConfiguration;
import com.wgluka.framework.container.BeanContainer;
import com.wgluka.framework.container.IoCContainer;

/**
 * Created by yukai on 2017/4/14.
 */
public class ApplicationContext {
    public ApplicationContext(Class root){
        this(root.getPackage().getName());
    }

    public ApplicationContext(String basePackagePath){
        DefaultConfiguration.defaultAppBasePackage =basePackagePath;
        init();
    }

    public void init(){
        IoCContainer.init();
    }

    public <T> T getBean(Class<T> tClass){
        return BeanContainer.getBean(tClass);
    }

    public int countBean(){
        return BeanContainer.size();
    }
}
