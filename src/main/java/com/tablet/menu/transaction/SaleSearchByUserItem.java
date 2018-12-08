package com.tablet.menu.transaction;

import com.modelsale.model.Transaction;
import com.modelsale.model.User;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.IListRepository;
import com.tablet.repository.domain.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@SaleMenuItem
public class SaleSearchByUserItem implements IMenuItem {

    private final ITransactionRepository itransactionRepository;

    @Autowired
    public SaleSearchByUserItem(ITransactionRepository itransactionRepository) {
        this.itransactionRepository = itransactionRepository;
    }

    @Override
    public String getTitle() {
        return "Search salesByUser";
    }

    @Override
    @Transactional
    public int doAction() {
        itransactionRepository.salesByUser().forEach(System.out::println);
        return 0;
    }
}
