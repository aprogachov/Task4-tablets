package com.tablet.menu.transaction;

import com.modelsale.model.Transaction;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.IListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@SaleMenuItem
public class SaleSearchByIdMenuItem implements IMenuItem {

    private final IListRepository<Transaction> transactionRepository;
    private final MenuHelper menuHelper;

    @Autowired
    public SaleSearchByIdMenuItem(IListRepository<Transaction> transactionRepository, MenuHelper menuHelper) {
        this.transactionRepository = transactionRepository;
        this.menuHelper = menuHelper;
    }

    @Override
    public String getTitle() {
        return "Search by id";
    }

    @Override
    @Transactional
    public int doAction() {
        System.out.println("Enter transaction id:");
        int id = menuHelper.readInt();
        Transaction transaction = transactionRepository.findById(id);
        if (transaction == null) {
            System.out.println("transaction not found");
        } else {
            System.out.println(transaction);
        }
        return 0;
    }

    @Override
    public int priority() {
        return 3;
    }
}
