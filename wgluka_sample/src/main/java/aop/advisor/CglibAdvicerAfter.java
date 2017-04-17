package aop.advisor;

import com.wgluka.framework.aop.advice.AfterAdvicer;
import com.wgluka.framework.aop.annotation.After;
import com.wgluka.framework.aop.annotation.Aspect;

/**
 * Created by yukai on 2017/4/17.
 */
@Aspect
@After(className = "aop.service.CglibService")
public class CglibAdvicerAfter implements AfterAdvicer {
    @Override
    public void after() {
        System.out.println("cglib after");
    }
}
