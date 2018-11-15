package com.tablet.repository.domain.impl;

import com.modelsale.model.Patient;
import com.modelsale.model.Product;
import com.modelsale.model.Transaction;
import com.tablet.exception.StateException;
import com.tablet.repository.AbstractListRepository;
import com.tablet.repository.domain.ITransactionRepository;
import com.tablet.util.Audit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class TransactionRepository extends AbstractListRepository<Transaction> implements ITransactionRepository {

    @Override
    protected Class<Transaction> getEntityClass() {
        return Transaction.class;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Audit(action = "Find all transactions")
    public List<Transaction> findAll() {
        return super.findAll();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Audit(action = "FindById transaction")
    public Transaction findById(Integer transactionId) {
        return super.findById(transactionId);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Audit(action = "SALE")
    public void sale(Product product, Patient patient) {
        if (!product.getState().equals(patient.getState())) {
            try {
                throw new StateException("THE PRODUCT IS NOT FOR SALE IN THIS STATE");
            } catch (StateException se) {
                System.out.println(se);
            }
        }

        Transaction sale = new Transaction(patient, product, new Date());

        em.persist(sale);
    }
}
