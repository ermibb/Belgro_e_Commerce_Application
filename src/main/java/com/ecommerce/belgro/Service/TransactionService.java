package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Model.Order;
import com.ecommerce.belgro.Model.Seller;
import com.ecommerce.belgro.Model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySellerId(Seller seller);
    List<Transaction> getAllTransaction();
}
