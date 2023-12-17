package com.obibia.ewallet.services;

import com.obibia.ewallet.data.models.Transaction;
import com.obibia.ewallet.data.models.User;
import com.obibia.ewallet.data.repositories.UserRepository;
import com.obibia.ewallet.dto.*;
import com.obibia.ewallet.exceptions.EwalletException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final TransactionService transactionService;
    @Override
    public UserCreationResponse register(CreateAccountRequest createAccountRequest) {
        if (userRepository.findUserByEmail(createAccountRequest.getEmail()).isPresent()){
            throw new EwalletException("Email already exists", 400);
        }
        User user = User.builder()
                .email(createAccountRequest.getEmail())
                .phoneNumber(createAccountRequest.getPhoneNumber())
                .firstName(createAccountRequest.getFirstName())
                .lastName(createAccountRequest.getLastName())
                .password(createAccountRequest.getPassword())
                .balance(BigDecimal.ZERO).build();

        userRepository.save(user);
        return UserCreationResponse.builder()
                .message("User Created Successfully")
                .status("200")
                .data(user)
                .build();
    }

    @Override
    public String login(LoginRequest loginRequest) {

        return null;
    }

    @Override
    public Transaction deposit(DepositRequest depositRequest) {

        User foundUser = userRepository.findUserByPhoneNumber(depositRequest.getPhoneNumber()).get();

        if(foundUser != null){
            if (depositRequest.getAmount() < 0.0){
                throw new EwalletException("cant deposit, amount too low", 400);
            }
            BigDecimal amount = BigDecimal.valueOf(depositRequest.getAmount());
           BigDecimal newBalance = foundUser.getBalance().add(amount);
           foundUser.setBalance(newBalance);

           Transaction transaction = transactionReceipt("Deposit", foundUser, amount);
            userRepository.save(foundUser);
            return transaction;



        }
        throw new EwalletException("invalid user details", 400);

    }

    private Transaction transactionReceipt(String transactionType, User foundUser, BigDecimal amount) {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setTransactionType(transactionType);
        transactionRequest.setAmount(amount);
        transactionRequest.setUser(foundUser);
        return transactionService.saveTransactions(transactionRequest);
    }

    @Override
    public Transaction withdrawal(WithdrawalRequest withdrawalRequest) {
        User foundUser = userRepository.findUserByPhoneNumber(withdrawalRequest.getPhoneNumber()).get();

        if(foundUser.getPin() == withdrawalRequest.getPin()){
            int comparedAmount = foundUser.getBalance().compareTo(BigDecimal.valueOf(withdrawalRequest.getAmount()));
            if ( comparedAmount <= 0){
                throw new EwalletException("cant withdraw, amount too low", 400);
            }
            BigDecimal amount = BigDecimal.valueOf(withdrawalRequest.getAmount());
           BigDecimal newBalance = foundUser.getBalance().subtract(amount);
           foundUser.setBalance(newBalance);
            userRepository.save(foundUser);
            return transactionReceipt("Withdrawal", foundUser, amount);

        }
        throw new EwalletException("invalid user details", 400);

    }

    @Override
    public String createPin( CreatePinRequest createPinRequest) {
        User foundUser = userRepository.findUserByPhoneNumber(createPinRequest.getPhoneNumber()).get();
        foundUser.setPin(createPinRequest.getPin());
        userRepository.save(foundUser);
        return "Pin created successfully";
    }

    @Override
    public String forgotPin(ForgotPinRequest changePinRequest) {
        User foundUser = userRepository.findUserByPhoneNumber(changePinRequest.getPhoneNumber()).get();
        foundUser.setPin(changePinRequest.getNewPin());
        userRepository.save(foundUser);
        return "Pin changed successfully";
    }

    @Override
    public String changePassword(String password, String newPassword) {
        return null;
    }

    @Override
    public ArrayList<Transaction> findAllTransactions(String phoneNumber) {
        User foundUser = userRepository.findUserByPhoneNumber(phoneNumber).get();
        return transactionService.findAllTransactions(foundUser);

    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
