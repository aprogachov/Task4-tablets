package com.tablet.menu.product;

import com.modelsale.model.Product;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.ICrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@ProductMenuItem
public class ProductSearchByIdMenuItem implements IMenuItem {

    private final ICrudRepository<Product> productRepository;
    private final MenuHelper menuHelper;

    @Autowired
    public ProductSearchByIdMenuItem(ICrudRepository<Product> productRepository, MenuHelper menuHelper) {
        this.productRepository = productRepository;
        this.menuHelper = menuHelper;
    }

    @Override
    public String getTitle() {
        return "Search by id";
    }

    @Override
    @Transactional
    public int doAction() {
        System.out.println("Enter product id:");
        int id = menuHelper.readInt();
        Product product = productRepository.findById(id);
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
