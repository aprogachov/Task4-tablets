package com.tablet.menu.state;

import com.modelsale.model.State;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.domain.IStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@StateMenuItem
public class StateSearchByCodeMenuItem implements IMenuItem {

    private final IStateRepository istateRepository;
    private final MenuHelper menuHelper;

    @Autowired
    public StateSearchByCodeMenuItem(IStateRepository istateRepository, MenuHelper menuHelper) {
        this.istateRepository = istateRepository;
        this.menuHelper = menuHelper;
    }

    @Override
    public String getTitle() {
        return "Search by code";
    }

    @Override
    @Transactional
    public int doAction() {
        System.out.println("Enter state code:");
        String code = menuHelper.read();
        State state = istateRepository.findByCode(code);
        if (state == null) {
            System.out.println("state not found");
        } else {
            System.out.println(state);
        }
        return 0;
    }

    @Override
    public int priority() {
        return 3;
    }
}
