package com.tablet.menu.product;

import com.modelsale.model.Product;
import com.modelsale.model.State;
import com.tablet.menu.util.ConsoleFactory;
import com.tablet.menu.util.MenuHelper;
import com.tablet.repository.domain.IStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConsoleFactory implements ConsoleFactory<Product> {

    private final MenuHelper menuHelper;
    private final IStateRepository istateRepository;

    @Autowired
    public ProductConsoleFactory(
            MenuHelper menuHelper,
            IStateRepository istateRepository) {
        this.menuHelper = menuHelper;
        this.istateRepository = istateRepository;
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

        State state = istateRepository.findByCode(stateCode);

        product.setName(name);
        product.setState(state);
    }
}
