package com.tablet.menu.product;

import com.modelsale.model.Product;
import com.modelsale.model.State;
import com.tablet.menu.util.ConsoleFactory;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.domain.impl.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConsoleFactory implements ConsoleFactory<Product> {

    private final MenuHelper menuHelper;
    private final StateRepository stateRepository;



    @Autowired
    public ProductConsoleFactory(
            MenuHelper menuHelper,
            StateRepository stateRepository) {
        this.menuHelper = menuHelper;
        this.stateRepository = stateRepository;
    }

    @Override
    public Product create() {
        Product product = new Product();
        update(product);
        return product;
    }

    @Override
    public void update(Product product) {
        System.out.println("Input name:");
        String name = menuHelper.read();


        System.out.println("Input state code");
        String stateCode = menuHelper.read();

        State state = stateRepository.findByCode(stateCode);

        product.setName(name);
        product.setState(state);
    }
}
