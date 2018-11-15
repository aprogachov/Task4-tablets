package com.tablet.repository.domain.impl;

import com.modelsale.model.Product;
import com.tablet.repository.AbstractCrudRepository;
import com.tablet.repository.domain.IProductRepository;
import com.tablet.util.Audit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductRepository extends AbstractCrudRepository<Product> implements IProductRepository {

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Audit(action = "Add product")
    public void create(Product product) {
        super.create(product);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Audit(action = "Update product")
    public void update(Product product) {
        super.update(product);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Audit(action = "DeleteById product")
    public void deleteById(Integer productId) {
        super.deleteById(productId);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Audit(action = "Find all products")
    public List<Product> findAll() {
        return super.findAll();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Audit(action = "FindById product")
    public Product findById(Integer patientId) {
        return super.findById(patientId);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Audit(action = "FindByName product")
    public Product findByName(String name) {
        TypedQuery<Product> query = em.createQuery("select p from product p where p.name = :name", Product.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

}
