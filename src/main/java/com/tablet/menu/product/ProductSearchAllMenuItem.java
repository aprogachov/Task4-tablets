package com.tablet.menu.product;

import com.modelsale.model.Product;
import com.tablet.menu.IMenuItem;
import com.tablet.repository.IListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@ProductMenuItem
public class ProductSearchAllMenuItem implements IMenuItem {

    private final IListRepository<Product> productRepository;

    @Autowired
    public ProductSearchAllMenuItem(IListRepository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String getTitle() {
        return "Print all products";
    }

    @Override
    @Transactional
    public int doAction() {
        productRepository.findAll().forEach(System.out::println);
        return 0;
    }
}
