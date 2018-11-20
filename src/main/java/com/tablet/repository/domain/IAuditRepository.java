package com.tablet.repository.domain;

import com.modelsale.model.IEntity;
import com.modelsale.model.User;
import com.tablet.repository.IListRepository;
import com.modelsale.model.AuditOperation;

public interface IAuditRepository extends IListRepository<AuditOperation> {
    void create(boolean status, User user, Object... params);
}
