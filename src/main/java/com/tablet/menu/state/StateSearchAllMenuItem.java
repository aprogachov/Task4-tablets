package com.tablet.menu.state;

import com.tablet.menu.IMenuItem;
import com.tablet.repository.domain.impl.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@StateMenuItem
public class StateSearchAllMenuItem implements IMenuItem {

    private final StateRepository stateRepository;

    @Autowired
    public StateSearchAllMenuItem(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public String getTitle() {
        return "Print all states";
    }

    @Override
    @Transactional
    public int doAction() {
        stateRepository.findAll().forEach(System.out::println);
        return 0;
    }
}
