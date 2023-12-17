package com.obibia.ewallet.services;

import com.obibia.ewallet.data.models.Transaction;
import com.obibia.ewallet.dto.*;

import java.util.ArrayList;

public interface UserService {
    UserCreationResponse register(CreateAccountRequest createAccountRequest);

    String login(LoginRequest loginRequest);

    Transaction deposit(DepositRequest depositRequest);

    Transaction withdrawal(WithdrawalRequest withdrawalRequest);

    String createPin( CreatePinRequest createPinRequest);

    String forgotPin(ForgotPinRequest changePinRequest);

    String changePassword(String password, String newPassword);

    ArrayList<Transaction> findAllTransactions(String phoneNumber);

    void deleteAll();
}
