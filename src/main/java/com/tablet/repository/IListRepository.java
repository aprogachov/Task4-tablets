package com.tablet.repository;

import com.modelsale.model.IEntity;

import java.util.List;

public interface IListRepository<E extends IEntity> {
    List<E> findAll();
    E findById(Integer id);
}
