package com.tablet.menu.state;

import com.modelsale.model.State;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.IListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@StateMenuItem
public class StateSearchByIdMenuItem implements IMenuItem {

    private final IListRepository<State> stateRepository;
    private final MenuHelper menuHelper;

    @Autowired
    public StateSearchByIdMenuItem(IListRepository<State> stateRepository, MenuHelper menuHelper) {
        this.stateRepository = stateRepository;
        this.menuHelper = menuHelper;
    }

    @Override
    public String getTitle() {
        return "Search by id";
    }

    @Override
    @Transactional
    public int doAction() {
        System.out.println("Enter state id:");
        int id = menuHelper.readInt();
        State state = stateRepository.findById(id);
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
