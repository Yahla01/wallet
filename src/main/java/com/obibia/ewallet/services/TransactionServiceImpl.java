package com.obibia.ewallet.services;

import com.obibia.ewallet.data.models.Transaction;
import com.obibia.ewallet.data.models.TransactionType;
import com.obibia.ewallet.data.models.User;
import com.obibia.ewallet.data.repositories.TransactionRepository;
import com.obibia.ewallet.dto.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransactions(TransactionRequest transactionRequest) {

        Transaction transaction = new Transaction();
        transaction.setDate(LocalDateTime.now());
        transaction.setAmount(transactionRequest.getAmount());
        if (transactionRequest.getTransactionType().equals("Deposit"))transaction.setTransactionType(TransactionType.DEPOSIT);
        if (transactionRequest.getTransactionType().equals("Withdrawal"))transaction.setTransactionType(TransactionType.WITHDRAWAL);
       transaction.setUser(transactionRequest.getUser());
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public ArrayList<Transaction> findAllTransactions(User user) {
        return transactionRepository.findAllByUser(user);

    }
}
