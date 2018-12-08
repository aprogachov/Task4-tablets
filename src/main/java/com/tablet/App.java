package com.tablet;

import com.tablet.authorization.IUserAuthorization;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
   
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.registerShutdownHook();

        if (context.getBean(IUserAuthorization.class).findUser()) {
            Runnable mainMenu = (Runnable) context.getBean("mainMenu");
            mainMenu.run();
        }

    context.close();
    }
    
}
