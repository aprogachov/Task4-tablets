package com.tablet.menu.user;

import com.modelsale.model.User;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.IListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@UserMenuItem
public class UserSearchByIdMenuItem implements IMenuItem {

    private final IListRepository<User> userRepository;
    private final MenuHelper menuHelper;

    @Autowired
    public UserSearchByIdMenuItem(IListRepository<User> userRepository, MenuHelper menuHelper) {
        this.userRepository = userRepository;
        this.menuHelper = menuHelper;
    }

    @Override
    public String getTitle() {
        return "Search by id";
    }

    @Override
    @Transactional
    public int doAction() {
        System.out.println("Enter user id:");
        int id = menuHelper.readInt();
        User user = userRepository.findById(id);
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
