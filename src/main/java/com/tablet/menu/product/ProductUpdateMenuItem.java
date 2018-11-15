package com.tablet.menu.product;

import com.modelsale.model.Product;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.ConsoleFactory;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.ICrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@ProductMenuItem
public class ProductUpdateMenuItem implements IMenuItem {

    private final ConsoleFactory<Product> productConsoleFactory;
    private final ICrudRepository<Product> productRepository;
    private final MenuHelper menuHelper;

    @Autowired
    public ProductUpdateMenuItem(
            ConsoleFactory<Product> productConsoleFactory,
            ICrudRepository<Product> productRepository,
            MenuHelper helper
    ) {
        this.productConsoleFactory = productConsoleFactory;
        this.productRepository = productRepository;
        this.menuHelper = helper;
    }

    @Override
    public String getTitle() {
        return "Update product";
    }

    @Override
    @Transactional
    public int doAction() {
        System.out.println("Enter product id:");
        int id = menuHelper.readInt();
        Product product = productRepository.findById(id);
        if (product == null) {
            System.out.println("product not found");
            return 0;
        } else {
            System.out.println(product);
        }
        productConsoleFactory.update(product);
        productRepository.update(product);
        return 0;
    }
}
