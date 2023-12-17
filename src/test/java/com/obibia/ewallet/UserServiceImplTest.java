package com.obibia.ewallet;

import com.obibia.ewallet.data.models.Transaction;
import com.obibia.ewallet.data.models.User;
import com.obibia.ewallet.dto.CreateAccountRequest;
import com.obibia.ewallet.dto.DepositRequest;
import com.obibia.ewallet.dto.UserCreationResponse;
import com.obibia.ewallet.dto.WithdrawalRequest;
import com.obibia.ewallet.services.UserService;
import com.obibia.ewallet.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserService userService;

    private CreateAccountRequest createAccountRequest;
    private DepositRequest depositRequest = new DepositRequest();
    private WithdrawalRequest withdrawalRequest = new WithdrawalRequest();

    private UserCreationResponse creationResponse;

    @BeforeEach
    public void setUp(){

        createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setFirstName("Josh");
        createAccountRequest.setLastName("kuse");
        createAccountRequest.setPhoneNumber("07033490197");
        createAccountRequest.setEmail("kuse");
        createAccountRequest.setPassword("1234");

        depositRequest.setPhoneNumber(createAccountRequest.getPhoneNumber());
        depositRequest.setAmount(1000.00);

        withdrawalRequest.setAmount(500.00);
        withdrawalRequest.setPin(0);
        withdrawalRequest.setPhoneNumber(createAccountRequest.getPhoneNumber());

    }

    @Test
    public void testThatCustomerCanRegister() {
        userService.deleteAll();
        creationResponse = userService.register(createAccountRequest);
        assertThat(creationResponse).isNotNull();
    }

    @Test
    public void testThatCustomerCanDeposit() {
        userService.deleteAll();
        creationResponse = userService.register(createAccountRequest);
        Transaction transaction = userService.deposit(depositRequest);
        assertThat(transaction).isNotNull();
    }

    @Test
    public void testThatCustomerCanWithdrawal() {
        userService.deleteAll();
        creationResponse = userService.register(createAccountRequest);
        Transaction transaction = userService.deposit(depositRequest);
        Transaction transaction1 = userService.withdrawal(withdrawalRequest);
        assertThat(transaction).isNotNull();
    }

}
