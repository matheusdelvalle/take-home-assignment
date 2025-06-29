package com.matheusdelvalle.test.testecase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matheusdelvalle.test.testecase.DTO.EventRequest;
import com.matheusdelvalle.test.testecase.DTO.EventResponse;
import com.matheusdelvalle.test.testecase.services.AccountService;

@RestController
public class EventController {

	@Autowired
    private AccountService accountService;

	@PostMapping("/event")
	public ResponseEntity<Object> event(@RequestBody EventRequest eventRequest) {
				
		EventResponse response = accountService.IncomingEvent(eventRequest);

		return ResponseEntity
			.status(response != null ? HttpStatus.CREATED : HttpStatus.NOT_FOUND) // or HttpStatus.OK
			.body(response != null ? response : "0");
	}
    
}