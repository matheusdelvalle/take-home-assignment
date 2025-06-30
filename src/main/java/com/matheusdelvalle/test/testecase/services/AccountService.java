package com.matheusdelvalle.test.testecase.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusdelvalle.test.testecase.DTO.EventRequest;
import com.matheusdelvalle.test.testecase.DTO.EventResponse;
import com.matheusdelvalle.test.testecase.models.Account;
import com.matheusdelvalle.test.testecase.repositories.AccountMemoryRepository;


@Service
public class AccountService {

    @Autowired
    private AccountMemoryRepository accountRepository;

    @Autowired
    private List<AccountOperation> accountOperations;

    public void resetDataBase(){
        accountRepository.reset();
    };

    public EventResponse IncomingEvent(EventRequest eventRequest){

        for (AccountOperation accountOperation : accountOperations) {
            if (accountOperation.getOperation() == eventRequest.getType()) {
                return accountOperation.execute(eventRequest);
            }
        }

        return null;
    }

    public String getBalance (String id){

        Account account = accountRepository.getById(id);

        if (account == null)
            return "";

        return String.valueOf(account.getBalance());
    }    
}
