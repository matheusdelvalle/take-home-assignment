package com.matheusdelvalle.test.testecase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matheusdelvalle.test.testecase.DTO.EventRequest;
import com.matheusdelvalle.test.testecase.DTO.EventResponse;
import com.matheusdelvalle.test.testecase.emums.Operation;
import com.matheusdelvalle.test.testecase.models.Account;
import com.matheusdelvalle.test.testecase.repositories.AccountMemoryRepository;

@Component
public class DepositOperation implements AccountOperation {

    @Autowired
    private AccountMemoryRepository accountRepository;

    @Override
    public EventResponse execute(EventRequest eventRequest) {
       Account account = accountRepository.getById(eventRequest.getDestination());

        if (account == null){
            
            account = accountRepository.addNewAccount(eventRequest.getDestination(), String.valueOf(eventRequest.getAmount()));

            return new EventResponse(null, account );
        }

        int newBalance = account.getBalance() + eventRequest.getAmount();
        account.setBalance(newBalance);

        Account updatedAccount = accountRepository.updateAccount(account);
        
        return new EventResponse(null, updatedAccount);
    }

    @Override
    public Operation getOperation() {
        return Operation.deposit;
    }
    
}
