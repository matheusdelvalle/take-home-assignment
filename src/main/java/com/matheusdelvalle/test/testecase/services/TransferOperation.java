package com.matheusdelvalle.test.testecase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matheusdelvalle.test.testecase.DTO.EventRequest;
import com.matheusdelvalle.test.testecase.DTO.EventResponse;
import com.matheusdelvalle.test.testecase.emums.Operation;
import com.matheusdelvalle.test.testecase.models.Account;
import com.matheusdelvalle.test.testecase.repositories.AccountMemoryRepository;

@Component
public class TransferOperation implements AccountOperation {

    @Autowired 
    private AccountMemoryRepository accountRepository;

    @Override
    public EventResponse execute(EventRequest eventRequest) {
        Account origin = accountRepository.getById(eventRequest.getOrigin());
        Account destination = accountRepository.getById(eventRequest.getDestination());

        if (origin == null){
            return null;
        }

        if (destination == null){
            destination = accountRepository.addNewAccount(eventRequest.getDestination(), 0 + "");
        }

        int newOriginBalance = origin.getBalance() - eventRequest.getAmount();
        int newDestinationBalance = destination.getBalance() + eventRequest.getAmount();

        origin.setBalance(newOriginBalance);
        destination.setBalance(newDestinationBalance);

        Account updatedOrigin = accountRepository.updateAccount(origin);
        Account updatedDestination = accountRepository.updateAccount(destination);

        if (updatedOrigin != null && updatedDestination != null) {
            return new EventResponse(updatedOrigin, updatedDestination);
        }

        return null;
    }

    @Override
    public Operation getOperation() {
        return Operation.TRANSFER;
    }
    
}
