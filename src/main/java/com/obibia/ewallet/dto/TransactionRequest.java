package com.obibia.ewallet.dto;

import com.obibia.ewallet.data.models.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {
    private String transactionType;
    private BigDecimal amount;
    private User user;
}

