package com.ecommerce.belgro.Service.Impl;

import com.ecommerce.belgro.Model.Order;
import com.ecommerce.belgro.Model.Seller;
import com.ecommerce.belgro.Model.Transaction;
import com.ecommerce.belgro.Repository.SellerRepository;
import com.ecommerce.belgro.Repository.TransactionRepository;
import com.ecommerce.belgro.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    final TransactionRepository transactionRepository;
    final SellerRepository sellerRepository;
    @Override
    public Transaction createTransaction(Order order) {

        Seller seller = sellerRepository.findById(order.getSellerId()).get();

        Transaction transaction = new Transaction();
        transaction.setSeller(seller);
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionBySellerId(Seller seller) {
        return transactionRepository.findBySellerId(seller.getId());
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
}
