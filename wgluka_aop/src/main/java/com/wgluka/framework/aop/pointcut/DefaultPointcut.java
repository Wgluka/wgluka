package com.wgluka.framework.aop.pointcut;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yukai on 2017/4/16.
 */
public class DefaultPointcut implements Pointcut {

    private Pattern classPattern;
    private Pattern methodPattern;

    //正则表达式
    public DefaultPointcut(String regex) {
        String classAndMethod = regex.substring(regex.indexOf(" ") + 1, regex.indexOf("("));
        if (classAndMethod.indexOf(".") < 0)
            throw new RuntimeException("error regex pattern: " + regex);

        this.classPattern = Pattern.compile(classAndMethod.substring(0, classAndMethod.lastIndexOf(".")));
        this.methodPattern = Pattern.compile(classAndMethod.substring(classAndMethod.lastIndexOf(".") + 1));
    }

    public DefaultPointcut(String classPattern, String methodPattern) {
        this.classPattern = Pattern.compile(classPattern);
        this.methodPattern = Pattern.compile(methodPattern);
    }

    @Override
    public boolean matches(Class<?> clazz, Method method) {
        Matcher classMatcher = classPattern.matcher(clazz.getName());
        Matcher methodMatcher = methodPattern.matcher(method.getName());

        return classMatcher.matches() && methodMatcher.matches();
    }
}
