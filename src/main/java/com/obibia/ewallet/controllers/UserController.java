package com.obibia.ewallet.controllers;

import com.obibia.ewallet.data.models.User;
import com.obibia.ewallet.dto.CreateAccountRequest;
import com.obibia.ewallet.dto.UserCreationResponse;
import com.obibia.ewallet.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("api/ewallet/")
    @RequiredArgsConstructor
    public class UserController {
        private final UserServiceImpl userService;
        @PostMapping("create")
        public ResponseEntity<UserCreationResponse> createUser(@RequestBody CreateAccountRequest creationAccountRequest){

            UserCreationResponse userCreationResponse = userService.register(creationAccountRequest);
            return ResponseEntity.ok(userCreationResponse);
        }

       // public ResponseEntity<String> loginUser (@RequestBody User user) {
         //   if (userService.register(user)) {
           //     return new ResponseEntity<>("Login Successful", HttpStatus.OK);
            //} else {
              //  return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
            //}
       // }
    }


