package com.tablet.repository;

import com.modelsale.model.IEntity;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractCrudRepository<E extends IEntity>
        extends AbstractListRepository<E>
        implements ICrudRepository<E> {

    @Override
    @Transactional
    public void create(E e) {
        addCreatedBy(e);
        em.persist(e);
    }

    @Override
    @Transactional
    public void update(E e) {
        addUpdatedBy(e);
        em.merge(e);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        E e = em.find(getEntityClass(), id);
        if (e == null) {
            System.out.println("not found");
        }

        em.remove(e);
    }

    protected abstract void addCreatedBy(E e);

    protected abstract void addUpdatedBy(E e);

}
