package com.salereport.mapper;

import com.modelsale.model.Transaction;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TransactionSaleMapper {
    List<Transaction> getTransactions(@Param("dateFrom") Date dateFrom, @Param("dateBefore") Date dateBefore);
}
