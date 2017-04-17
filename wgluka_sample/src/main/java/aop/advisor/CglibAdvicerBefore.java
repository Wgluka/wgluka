package aop.advisor;

import com.wgluka.framework.aop.advice.BeforeAdvicer;
import com.wgluka.framework.aop.annotation.Aspect;
import com.wgluka.framework.aop.annotation.Before;

/**
 * Created by yukai on 2017/4/17.
 */
@Aspect
@Before(className = "aop.service.CglibService")
public class CglibAdvicerBefore implements BeforeAdvicer {
    @Override
    public void before() {
        System.out.println("cglib before");
    }
}
