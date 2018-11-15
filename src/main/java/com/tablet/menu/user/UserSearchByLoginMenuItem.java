package com.tablet.menu.user;

import com.modelsale.model.User;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.domain.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@UserMenuItem
public class UserSearchByLoginMenuItem implements IMenuItem {

    private final IUserRepository iuserRepository;
    private final MenuHelper menuHelper;

    @Autowired
    public UserSearchByLoginMenuItem(IUserRepository iuserRepository, MenuHelper menuHelper) {
        this.iuserRepository = iuserRepository;
        this.menuHelper = menuHelper;
    }

    @Override
    public String getTitle() {
        return "Search by login";
    }

    @Override
    @Transactional
    public int doAction() {
        System.out.println("Enter user login:");
        String login = menuHelper.read();
        User user = iuserRepository.findByLogin(login);
        if (user == null) {
            System.out.println("user not found");
        } else {
            System.out.println(user);
        }
        return 0;
    }

    @Override
    public int priority() {
        return 3;
    }
}
