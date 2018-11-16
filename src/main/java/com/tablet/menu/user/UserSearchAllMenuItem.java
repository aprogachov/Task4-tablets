package com.tablet.menu.user;

import com.tablet.menu.IMenuItem;
import com.tablet.repository.domain.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@UserMenuItem
public class UserSearchAllMenuItem implements IMenuItem {

    private final IUserRepository iuserRepository;

    @Autowired
    public UserSearchAllMenuItem(IUserRepository iuserRepository) {
        this.iuserRepository = iuserRepository;
    }

    @Override
    public String getTitle() {
        return "Print all users";
    }

    @Override
    @Transactional
    public int doAction() {
        iuserRepository.findAll().forEach(System.out::println);
        return 0;
    }
}
