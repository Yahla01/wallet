package com.obibia.ewallet.dto;

import lombok.Data;

@Data
public class ForgotPinRequest {
    private int newPin;
    private String phoneNumber;
}
