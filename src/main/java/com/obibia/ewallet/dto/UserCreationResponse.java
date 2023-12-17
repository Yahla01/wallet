package com.obibia.ewallet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationResponse {
    private String message;
    private String status;
    private Object data;
}
