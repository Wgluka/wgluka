package IoC.model;

import com.wgluka.framework.annotation.Autowired;
import com.wgluka.framework.annotation.bean.Bean;

/**
 * Created by yukai on 2017/4/14.
 */
@Bean
public class Family {

    @Autowired
    private Father father;

   public void test(){
       if (father != null){
           System.out.println("family with father named " + father.getName());
       }
   }
}
