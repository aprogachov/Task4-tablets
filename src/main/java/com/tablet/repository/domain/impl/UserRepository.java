package com.tablet.repository.domain.impl;

import com.modelsale.model.User;
import com.tablet.repository.AbstractListRepository;
import com.tablet.repository.domain.IUserRepository;
import com.tablet.util.Audit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepository extends AbstractListRepository<User> implements IUserRepository {

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Audit(action = "Find all users")
    public List<User> findAll() {
        return super.findAll();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Audit(action = "FindById user")
    public User findById(Integer userId) {
        return super.findById(userId);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Audit(action = "FindByLogin user")
    public User findByLogin(String login) {
        TypedQuery<User> query = em.createQuery("select u from user u where u.login = :login", User.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

}
