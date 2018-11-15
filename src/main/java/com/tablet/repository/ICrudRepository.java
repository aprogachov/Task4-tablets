package com.tablet.repository;

import com.modelsale.model.IEntity;
import org.springframework.transaction.annotation.Transactional;

public interface ICrudRepository<E extends IEntity> extends IListRepository<E> {
    void create(E e);
    void update(E e);
    void deleteById(Integer id);
}
