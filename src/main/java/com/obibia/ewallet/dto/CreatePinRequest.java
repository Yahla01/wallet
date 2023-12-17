package com.obibia.ewallet.dto;

import lombok.Data;

@Data
public class CreatePinRequest {
    private int pin;
    private String phoneNumber;
}
