package aop;

import aop.service.AopInterfaceImpl;
import com.wgluka.framework.ApplicationContext;

/**
 * Created by yukai on 2017/4/16.
 */
public class Main {
    public static void main(String[] args){
        ApplicationContext context = new ApplicationContext(Main.class);
        AopInterfaceImpl aopInterface = context.getBean(AopInterfaceImpl.class);
        aopInterface.test();
    }
}
