package com.tablet.menu.patient;

import com.tablet.menu.AbstractMenu;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.TopLevelMenu;
import com.tablet.menu.util.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@TopLevelMenu
public class PatientTopMenu extends AbstractMenu implements IMenuItem {

    @Autowired
    public PatientTopMenu(@PatientMenuItem List<IMenuItem> topLevelItems, MenuHelper menuHelper) {
        super(topLevelItems, menuHelper);
    }

    @Override
    public String getTitle() {
        return "Patient management";
    }

    @Override
    public int doAction() {
        run();
        return 0;
    }
}
