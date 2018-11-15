package com.tablet.menu.transaction;

import com.tablet.menu.IMenuItem;
import com.tablet.repository.domain.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@SaleMenuItem
public class SaleSearchAllMenuItem implements IMenuItem {

    private final ITransactionRepository  itransactionRepository;

    @Autowired
    public SaleSearchAllMenuItem(ITransactionRepository itransactionRepository) {
        this.itransactionRepository = itransactionRepository;
    }

    @Override
    public String getTitle() {
        return "Print all sales";
    }

    @Override
    @Transactional
    public int doAction() {
        itransactionRepository.findAll().forEach(System.out::println);
        return 0;
    }
}
