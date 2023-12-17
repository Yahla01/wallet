package com.obibia.ewallet.data.repositories;

import com.obibia.ewallet.data.models.Transaction;
import com.obibia.ewallet.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    ArrayList<Transaction> findAllByUser(User user);
}
