package com.obibia.ewallet.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {

    private String phoneNumber;

    private double amount;
}
