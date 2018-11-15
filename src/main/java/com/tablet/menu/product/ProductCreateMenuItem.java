package com.tablet.menu.product;

import com.modelsale.model.Product;
import com.tablet.menu.IMenuItem;
import com.tablet.menu.util.ConsoleFactory;
import com.tablet.repository.ICrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProductMenuItem
public class ProductCreateMenuItem implements IMenuItem {

    private final ConsoleFactory<Product> productConsoleFactory;
    private final ICrudRepository<Product> productRepository;

    @Autowired
    public ProductCreateMenuItem(
            ConsoleFactory<Product> productConsoleFactory,
            ICrudRepository<Product> productRepository) {
        this.productConsoleFactory = productConsoleFactory;
        this.productRepository = productRepository;
    }

    @Override
    public String getTitle() {
        return "Add product";
    }

    @Override
    public int doAction() {
        Product product = productConsoleFactory.create();
        productRepository.create(product);
        return 0;
    }
}
