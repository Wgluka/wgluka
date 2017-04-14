package com.wgluka.framework.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by yukai on 2017/4/13.
 */
public class ClassLoaderUtilTest {

    @Test
    public void loadClass(){
        Class<?> clazz = ClassLoaderUtil.loadClass("com.wgluka.framework.util.ClassLoaderUtilTest", false);
        Assert.assertNotNull(clazz);
        Assert.assertTrue(clazz.getSimpleName().equals("ClassLoaderUtilTest"));
    }

    @Test
    public void getAllClass(){
        Set<Class<?>> set = ClassLoaderUtil.getAllClass("com.wgluka.framework");
        for (Class clazz: set){
            System.out.println(clazz.getName());
        }

        Assert.assertEquals(6, set.size());
    }

//    packageName=com.wgluka.framework
//    packagePath=/F:/Git/wgluka/target/classes/com/wgluka/framework
    @Test
    public void addClass(){
        String packageName = "com.wgluka.framework";
        String packagePath= "/F:/Git/wgluka/target/classes/com/wgluka/framework";
        Set<Class<?>> set = ClassLoaderUtil.addClass(packageName, packagePath);

        for (Class clazz: set){
            System.out.println(clazz.getName());
        }

        Assert.assertEquals(4, set.size());
    }
}
