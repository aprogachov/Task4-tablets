package com.tablet.authorization;

import com.modelsale.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserLoginHolder {

    private static ThreadLocal<User> handler = new ThreadLocal<>();

    public void login(User user) {
        handler.set(user);
    }

    public static User getCurrentUser() {
        return handler.get();
    }
}
