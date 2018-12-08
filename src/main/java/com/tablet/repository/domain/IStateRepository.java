package com.tablet.repository.domain;

import com.modelsale.model.State;
import com.tablet.repository.ICrudRepository;
import com.tablet.repository.IListRepository;

public interface IStateRepository extends IListRepository<State> {
    State findByCode(String code);
}
