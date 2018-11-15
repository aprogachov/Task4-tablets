package com.tablet.repository.domain.impl;

import com.modelsale.model.AuditOperation;
import com.modelsale.model.User;
import com.tablet.repository.AbstractListRepository;
import com.tablet.repository.domain.IAuditRepository;
import com.tablet.util.Audit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AuditRepository extends AbstractListRepository<AuditOperation> implements IAuditRepository {

    @Override
    protected Class<AuditOperation> getEntityClass() {
        return AuditOperation.class;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Audit(action = "Find all auditOperations")
    public List<AuditOperation> findAll() {
        return super.findAll();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Audit(action = "FindById auditOperation")
    public AuditOperation findById(Integer auditOperationId) {
        return super.findById(auditOperationId);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void create(User user, boolean status, Object... params) {
        String action = Arrays.stream(params)
                .map(Object::toString)
                .collect(Collectors.joining(";"));
        action = user.getLogin() + " : " + action;

        AuditOperation record = new AuditOperation(new Date(), status, action);

        em.persist(record);
    }
}
