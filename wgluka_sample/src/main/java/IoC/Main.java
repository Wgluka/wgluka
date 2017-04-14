package IoC;

import IoC.model.Family;
import IoC.model.Father;
import IoC.model.Other;
import com.wgluka.framework.ApplicationContext;

/**
 * Created by yukai on 2017/4/14.
 */
public class Main {
    /**
      bean count 2
     here is just father only
     family with father named father
     other is null
     */
    public static void main(String[] args){
        ApplicationContext context = new ApplicationContext(Main.class);
        System.out.println("bean count " + context.countBean());

        Father father = context.getBean(Father.class);
        father.test();

        Family family = context.getBean(Family.class);
        family.test();

        Other other = context.getBean(Other.class);
        if (other != null)
            other.test();
        else
            System.out.println("other is null");
    }
}
