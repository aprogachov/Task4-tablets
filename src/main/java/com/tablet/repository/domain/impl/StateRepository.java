package com.tablet.repository.domain.impl;

import com.modelsale.model.State;
import com.tablet.authorization.UserLoginHolder;
import com.tablet.repository.AbstractCrudRepository;
import com.tablet.repository.AbstractListRepository;
import com.tablet.repository.domain.IStateRepository;
import org.springframework.stereotype.Repository;
import com.tablet.util.Audit;

import javax.persistence.TypedQuery;

@Repository
public class StateRepository extends AbstractListRepository<State> implements IStateRepository {

    @Override
    protected Class<State> getEntityClass() {
        return State.class;
    }

    @Override
    @Audit(action = "FindById state")
    public State findById(Integer stateId) {
        return super.findById(stateId);
    }

    @Override
    public State findByCode(String code) {
        TypedQuery<State> query = em.createQuery("select s from state s where s.code = :code", State.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }
}
