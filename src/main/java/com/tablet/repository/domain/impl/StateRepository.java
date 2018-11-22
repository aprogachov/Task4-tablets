package com.tablet.repository.domain.impl;

import com.modelsale.model.State;
import com.tablet.repository.AbstractListRepository;
import com.tablet.repository.domain.IStateRepository;
import org.springframework.stereotype.Repository;
import com.tablet.util.Audit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class StateRepository extends AbstractListRepository<State> implements IStateRepository {

    @Override
    protected Class<State> getEntityClass() {
        return State.class;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Audit(action = "Find all states")
    public List<State> findAll() {
        return super.findAll();
    }

    @Override
//    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Audit(action = "FindById state")
    public State findById(Integer stateId) {
        return super.findById(stateId);
    }

    @Override
//    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Audit(action = "FindByCode state")
    public State findByCode(String code) {
        TypedQuery<State> query = em.createQuery("select s from state s where s.code = :code", State.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }
}
