package com.matheusdelvalle.test.testecase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheusdelvalle.test.testecase.services.AccountService;

@RestController
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @PostMapping("/reset")
	public String reset() {

		accountService.resetDataBase();

		return "OK";
	}

    @GetMapping("/balance")
	public ResponseEntity<String> balance(@RequestParam (required = true) String account_id) {

		String balance = accountService.getBalance(account_id);
		
		return ResponseEntity
			.status((balance.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK)
			.body((balance.isEmpty()) ? "0" : balance);
	}
}
