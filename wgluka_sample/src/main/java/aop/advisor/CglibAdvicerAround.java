package aop.advisor;

import com.wgluka.framework.aop.advice.AroundAdvicer;
import com.wgluka.framework.aop.annotation.Around;
import com.wgluka.framework.aop.annotation.Aspect;

/**
 * Created by yukai on 2017/4/17.
 */
@Aspect
@Around(className = "aop.service.CglibService")
public class CglibAdvicerAround implements AroundAdvicer {
    @Override
    public void before() {
        System.out.println("cglib around before");
    }

    @Override
    public void after(Object result) {
        System.out.println("cglib around after");
    }
}
