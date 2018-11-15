package com.tablet.menu.auditoperation;

import com.tablet.menu.IMenuItem;
import com.tablet.repository.domain.IAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AuditMenuItem
public class AuditSearchAllMenuItem implements IMenuItem {

    private final IAuditRepository iauditRepository;

    @Autowired
    public AuditSearchAllMenuItem(IAuditRepository iauditRepository) {
        this.iauditRepository = iauditRepository;
    }

    @Override
    public String getTitle() {
        return "Print all auditOperations";
    }

    @Override
    @Transactional
    public int doAction() {
        iauditRepository.findAll().forEach(System.out::println);
        return 0;
    }
}
