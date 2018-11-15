package com.tablet.menu.user;

import com.tablet.menu.IMenuItem;
import com.tablet.repository.domain.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@UserMenuItem
public class UserSearchAllMenuItem implements IMenuItem {

    private final UserRepository userRepository;

    @Autowired
    public UserSearchAllMenuItem(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getTitle() {
        return "Print all users";
    }

    @Override
    @Transactional
    public int doAction() {
        userRepository.findAll().forEach(System.out::println);
        return 0;
    }
}
