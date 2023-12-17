package com.obibia.ewallet.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    private String id;
    private LocalDateTime date;
    private BigDecimal amount;
    private TransactionType transactionType;
    @ManyToOne
    private User user;
}
