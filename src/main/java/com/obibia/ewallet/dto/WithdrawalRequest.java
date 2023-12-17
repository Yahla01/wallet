package com.obibia.ewallet.dto;

import lombok.Data;

@Data
public class WithdrawalRequest {
    private String phoneNumber;
    private double amount;
    private int pin;
}
