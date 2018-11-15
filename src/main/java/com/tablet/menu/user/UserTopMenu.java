package com.tablet.menu.user;

import com.tablet.menu.AbstractMenu;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.TopLevelMenu;
import com.tablet.menu.util.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@TopLevelMenu
public class UserTopMenu extends AbstractMenu implements IMenuItem {

    @Autowired
    public UserTopMenu(@UserMenuItem List<IMenuItem> topLevelItems, MenuHelper menuHelper) {
        super(topLevelItems, menuHelper);
    }

    @Override
    public String getTitle() {
        return "User management";
    }

    @Override
    public int doAction() {
        run();
        return 0;
    }
}
