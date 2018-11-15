package com.tablet.menu.util;

import com.modelsale.model.IEntity;

public interface ConsoleFactory<E extends IEntity> {
    E create();

    void update(E entity);
}
