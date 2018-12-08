package com.tablet.repository.domain;

import com.modelsale.model.Product;
import com.modelsale.model.Transaction;
import com.tablet.repository.ICrudRepository;
import com.tablet.repository.IListRepository;
import com.modelsale.model.Patient;

import java.util.List;

public interface ITransactionRepository extends ICrudRepository<Transaction> {
    void sale(Product product, Patient patient);
    List<Transaction> salesByUser();
}
