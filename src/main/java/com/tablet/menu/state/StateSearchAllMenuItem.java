package com.tablet.menu.state;

import com.tablet.menu.IMenuItem;
import com.tablet.repository.domain.IStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@StateMenuItem
public class StateSearchAllMenuItem implements IMenuItem {

    private final IStateRepository istateRepository;

    @Autowired
    public StateSearchAllMenuItem(IStateRepository istateRepository) {
        this.istateRepository = istateRepository;
    }

    @Override
    public String getTitle() {
        return "Print all states";
    }

    @Override
    public int doAction() {
        istateRepository.findAll().forEach(System.out::println);
        return 0;
    }
}
