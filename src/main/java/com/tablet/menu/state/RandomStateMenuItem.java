package com.tablet.menu.state;

import com.modelsale.model.State;
import com.tablet.menu.IMenuItem;
import com.tablet.repository.domain.IStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Component
@StateMenuItem
public class RandomStateMenuItem implements IMenuItem {

    private final IStateRepository istateRepository;

    @Autowired
    public RandomStateMenuItem(IStateRepository istateRepository) {
        this.istateRepository = istateRepository;
    }

    @Override
    public String getTitle() {
        return "Print random state";
    }

    @Override
    @Transactional
    public int doAction() {
        List<State> all = istateRepository.findAll();
        int randomIndex = new Random().nextInt(all.size());
        State state = all.get(randomIndex);
        System.out.println(state);
        return 0;
    }
}
