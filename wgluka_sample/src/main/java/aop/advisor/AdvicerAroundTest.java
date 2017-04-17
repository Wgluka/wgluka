package aop.advisor;

import com.wgluka.framework.aop.advice.AroundAdvicer;
import com.wgluka.framework.aop.annotation.Around;
import com.wgluka.framework.aop.annotation.Aspect;

/**
 * Created by yukai on 2017/4/17.
 */
@Aspect
@Around(className = "aop.service.AopInterfaceImpl")
public class AdvicerAroundTest implements AroundAdvicer{
    @Override
    public void before() {
        System.out.println("around before");
    }

    @Override
    public void after(Object result) {
        System.out.println("around after");
    }
}
