package com.tablet.repository.domain.impl;

import com.modelsale.model.Product;
import com.tablet.authorization.UserLoginHolder;
import com.tablet.repository.AbstractCrudRepository;
import com.tablet.repository.domain.IProductRepository;
import com.tablet.util.Audit;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class ProductRepository extends AbstractCrudRepository<Product> implements IProductRepository {

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    @Audit(action = "Update product")
    public void update(Product product) {
        super.update(product);
    }

    @Override
    @Audit(action = "DeleteById product")
    public void deleteById(Integer productId) {
        super.deleteById(productId);
    }

    @Override
    @Audit(action = "FindById product")
    public Product findById(Integer patientId) {
        return super.findById(patientId);
    }

    @Override
    @Audit(action = "FindByName product")
    public Product findByName(String name) {
        TypedQuery<Product> query = em.createQuery("select p from product p where p.name = :name", Product.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    protected void addCreatedBy(Product product) {
        product.setCreatedBy(UserLoginHolder.getCurrentUser());
    }

    @Override
    protected void addUpdatedBy(Product product) {
        product.setUpdatedBy(UserLoginHolder.getCurrentUser());
    }
}
