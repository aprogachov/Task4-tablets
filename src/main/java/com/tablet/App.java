package com.tablet;

import com.tablet.authorization.IUserAuthorization;
import com.tablet.menu.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
   
    public static void main(String[] args) {                          

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.registerShutdownHook();

        context.getBean(IUserAuthorization.class).findUser();

        Runnable mainMenu = (Runnable) context.getBean(MainMenu.class);
        mainMenu.run();

    context.close();
    }
    
}
