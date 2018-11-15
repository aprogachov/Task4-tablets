package com.tablet.repository.domain;

import com.modelsale.model.IEntity;
import com.modelsale.model.User;
import com.tablet.repository.IListRepository;
import com.modelsale.model.AuditOperation;

public interface IAuditRepository extends IListRepository<AuditOperation> {
    void create(User user, boolean status, Object... params);
}
