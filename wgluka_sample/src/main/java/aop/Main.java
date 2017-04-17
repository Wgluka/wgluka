package aop;

import aop.service.AopInterface;
import aop.service.AopInterfaceImpl;
import aop.service.CglibService;
import com.wgluka.framework.ApplicationContext;

/**
 * Created by yukai on 2017/4/16.
 */
public class Main {
    public static void main(String[] args){
        ApplicationContext context = new ApplicationContext(Main.class);

        //使用jdk动态代理
        AopInterface aopInterface = context.getBean(AopInterfaceImpl.class);
        aopInterface.test();

        System.out.println();

        //使用cglib动态代理
        CglibService service = context.getBean(CglibService.class);
        service.test();
    }
}
