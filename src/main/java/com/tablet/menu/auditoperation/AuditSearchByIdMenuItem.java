package com.tablet.menu.auditoperation;

import com.modelsale.model.AuditOperation;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.IListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AuditMenuItem
public class AuditSearchByIdMenuItem implements IMenuItem {

    private final IListRepository<AuditOperation> auditOperationRepository;
    private final MenuHelper menuHelper;

    @Autowired
    public AuditSearchByIdMenuItem(IListRepository<AuditOperation> auditOperationRepository, MenuHelper menuHelper) {
        this.auditOperationRepository = auditOperationRepository;
        this.menuHelper = menuHelper;
    }

    @Override
    public String getTitle() {
        return "Search by id";
    }

    @Override
    @Transactional
    public int doAction() {
        System.out.println("Enter auditOperation id:");
        int id = menuHelper.readInt();
        AuditOperation auditOperation = auditOperationRepository.findById(id);
        if (auditOperation == null) {
            System.out.println("auditOperation not found");
        } else {
            System.out.println(auditOperation);
        }
        return 0;
    }

    @Override
    public int priority() {
        return 3;
    }
}
