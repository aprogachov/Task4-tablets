package com.tablet.repository.domain;

import com.modelsale.model.Product;
import com.tablet.repository.ICrudRepository;

public interface IProductRepository extends ICrudRepository<Product> {
    Product findByName(String name);
}
