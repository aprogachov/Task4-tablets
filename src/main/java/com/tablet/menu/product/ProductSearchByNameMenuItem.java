package com.tablet.menu.product;

import com.modelsale.model.Product;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.domain.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@ProductMenuItem
public class ProductSearchByNameMenuItem implements IMenuItem {

    private final IProductRepository iproductRepository;
    private final MenuHelper menuHelper;

    @Autowired
    public ProductSearchByNameMenuItem(IProductRepository iproductRepository, MenuHelper menuHelper) {
        this.iproductRepository = iproductRepository;
        this.menuHelper = menuHelper;
    }

    @Override
    public String getTitle() {
        return "Search by name";
    }

    @Override
    @Transactional
    public int doAction() {
        System.out.println("Enter product name:");
        String name = menuHelper.read();
        Product product = iproductRepository.findByName(name);
        if (product == null) {
            System.out.println("product not found");
        } else {
            System.out.println(product);
        }
        return 0;
    }

    @Override
    public int priority() {
        return 3;
    }
}
