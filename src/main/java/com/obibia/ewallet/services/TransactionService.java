package com.obibia.ewallet.services;

import com.obibia.ewallet.data.models.Transaction;
import com.obibia.ewallet.data.models.User;
import com.obibia.ewallet.dto.TransactionRequest;

import java.util.ArrayList;

public interface TransactionService {
   Transaction saveTransactions(TransactionRequest transactionRequest);
    ArrayList<Transaction> findAllTransactions(User user);
}
