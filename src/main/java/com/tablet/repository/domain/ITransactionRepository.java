package com.tablet.repository.domain;

import com.modelsale.model.IEntity;
import com.modelsale.model.Product;
import com.modelsale.model.Transaction;
import com.tablet.repository.IListRepository;
import com.modelsale.model.Patient;

public interface ITransactionRepository extends IListRepository<Transaction> {
    void sale(Product product, Patient patient);
}
