package IoC.model;

import com.wgluka.framework.annotation.bean.Bean;

/**
 * Created by yukai on 2017/4/14.
 */
@Bean
public class Father {
    private String name = "father";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void test(){
        System.out.println("here is just father only");
    }
}
