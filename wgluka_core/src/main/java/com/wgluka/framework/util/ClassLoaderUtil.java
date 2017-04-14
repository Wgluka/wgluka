package com.wgluka.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yukai on 2017/4/13.
 */
public class ClassLoaderUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClassLoaderUtil.class);

    /**
     * 通过类名反射来获得类对象
     *
     * @param name
     * @param isInitialized
     * @return
     */
    public static Class<?> loadClass(String name, boolean isInitialized) {
        Class clazz = null;
        try {
            clazz = Class.forName(name, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.info("load class " + name + " failure", e);
            throw new RuntimeException(e);
        }
        return clazz;
    }

    /**
     * 获得当前线程所属的classloader
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获得包packageName下所有的类
     *
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getAllClass(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            Enumeration<URL> urls = getClassLoader().
                    getResources(packageName.replace(".", "/"));

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                classSet.addAll(addClass(packageName, url.getPath()));
            }
        } catch (IOException e) {
            logger.info("fail to load all class", e);
            throw new RuntimeException(e);
        }

        return classSet;
    }

    /**
     * 递归添加所有的类
     *
     * @param packageName eg. com.wgluka.framework
     * @param packagePath eg. /F:/Git/wgluka/target/classes/com/wgluka/framework
     * @return
     */
    public static Set<Class<?>> addClass(String packageName, String packagePath) {
        Set<Class<?>> set = new HashSet<>();
        File packageFile = new File(packagePath);
        if (packageFile.exists()) {
            File[] files = packageFile.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isFile() && pathname.getName().endsWith(".class")
                            || pathname.isDirectory();
                }
            });

            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName().substring(0, file.getName().indexOf("."));
                    Class<?> clazz = loadClass(packageName + "." + fileName, false);
                    set.add(clazz);
                } else {
                    String childPackageName = packageName + "." + file.getName();
                    String childPackagePath = packagePath + "/" + file.getName();
                    Set<Class<?>> childSet = addClass(childPackageName, childPackagePath);
                    set.addAll(childSet);
                }
            }
        }

        return set;
    }

}
