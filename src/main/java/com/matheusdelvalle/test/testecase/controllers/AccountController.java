package com.matheusdelvalle.test.testecase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheusdelvalle.test.testecase.services.AccountService;



@RestController
public class AccountController {
    
    @Autowired
    private AccountService eventEbanxService;

    @GetMapping("/reset")
	public String reset() {

		eventEbanxService.resetDataBase();

		return "OK";
	}

    @GetMapping("/balance")
	public ResponseEntity<String> balance(@RequestParam (required = true) String account_id) {

		String balance = eventEbanxService.getBalance(account_id);
		
		return ResponseEntity
			.status((balance == null || balance.isEmpty()) ? 201 : 404) // or HttpStatus.OK
			.body(balance);
	}
}
