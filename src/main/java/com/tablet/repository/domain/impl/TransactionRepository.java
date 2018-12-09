package com.tablet.repository.domain.impl;

import com.modelsale.model.Patient;
import com.modelsale.model.Product;
import com.modelsale.model.Transaction;
import com.tablet.authorization.UserLoginHolder;
import com.tablet.repository.AbstractCrudRepository;
import com.tablet.repository.AbstractListRepository;
import com.tablet.repository.domain.ITransactionRepository;
import com.tablet.util.Audit;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Repository
public class TransactionRepository extends AbstractCrudRepository<Transaction> implements ITransactionRepository {

    @Override
    protected Class<Transaction> getEntityClass() {
        return Transaction.class;
    }

    @Override
    @Audit(action = "FindById transaction")
    public Transaction findById(Integer transactionId) {
        return super.findById(transactionId);
    }

    @Override
    @Audit(action = "SALE")
    public void sale(Product product, Patient patient) {
        if (!product.getState().getName().equals(patient.getState().getName())) {
            throw new IllegalArgumentException("THE PRODUCT IS NOT FOR SALE IN THIS STATE");
        }

        Transaction sale = new Transaction(patient, product, new Date());

        create(sale);
    }

    @Override
    protected void addCreatedBy(Transaction transaction) {
        transaction.setCreatedBy(UserLoginHolder.getCurrentUser());
    }

    @Override
    protected void addUpdatedBy(Transaction transaction) {
        transaction.setUpdatedBy(UserLoginHolder.getCurrentUser());
    }

    @Override
    public List<Transaction> salesByUser() {
        TypedQuery<Transaction> query =
                em.createQuery("select t from transaction t where t.createdBy = :createdBy", Transaction.class);
        query.setParameter("createdBy", UserLoginHolder.getCurrentUser());
        return query.getResultList();
    }

}
