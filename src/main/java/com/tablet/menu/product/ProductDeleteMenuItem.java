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
public class ProductDeleteMenuItem implements IMenuItem {

    private final ICrudRepository<Product> productRepository;
    private final MenuHelper menuHelper;

    @Autowired
    public ProductDeleteMenuItem(ICrudRepository<Product> productRepository, MenuHelper menuHelper) {
        this.productRepository = productRepository;
        this.menuHelper = menuHelper;
    }


    @Override
    public String getTitle() {
        return "Delete product";
    }

    @Override
    @Transactional
    public int doAction() {
        System.out.println("Input product id:");
        int id = menuHelper.readInt();
        Product product = productRepository.findById(id);
        if (product == null) {
            System.out.println("patient not found");
        } else {
            productRepository.deleteById(id);
        }
        
        return 0;
    }

    @Override
    public int priority() {
        return 2;
    }
}
