package com.matheusdelvalle.test.testecase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusdelvalle.test.testecase.DTO.EventRequest;
import com.matheusdelvalle.test.testecase.DTO.EventResponse;
import com.matheusdelvalle.test.testecase.models.Account;
import com.matheusdelvalle.test.testecase.repositories.AccountRepository;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    public void resetDataBase(){
        accountRepository.reset();
    };

    public EventResponse IncomingEvent(EventRequest eventRequest){

        EventResponse response = null;

        switch (eventRequest.getType()) {
            case transfer:
                response = Transfer(eventRequest);
                break;
            case withdraw:
                response = Withdraw(eventRequest);
                break;
            case deposit:
                response = Deposit(eventRequest);
                break;
            default:
                break;
        }

        return response;
    }

    public EventResponse Transfer(EventRequest eventRequest){

        Account origin = accountRepository.getById(eventRequest.getOrigin());
        Account destination = accountRepository.getById(eventRequest.getDestination());

        if (origin == null || destination == null){
            return null;
        }

        double newOriginBalance = origin.getBalance() - eventRequest.getAmount();
        double newDestinationBalance = destination.getBalance() + eventRequest.getAmount();

        origin.setBalance(newOriginBalance);
        destination.setBalance(newDestinationBalance);

        Account updatedOrigin = accountRepository.updateAccount(origin);
        Account updatedDestination = accountRepository.updateAccount(destination);

        if (updatedOrigin != null && updatedDestination != null) {
            return new EventResponse(updatedOrigin, updatedDestination);
        }

        return null;
    }

    public EventResponse Withdraw(EventRequest eventRequest){

        Account account = accountRepository.getById(eventRequest.getDestination());

        if (account == null){
            return null;
        }

        double newBalance = account.getBalance() - eventRequest.getAmount();
        account.setBalance(newBalance);

        Account updatedAccount = accountRepository.updateAccount(account);
        
        if (updatedAccount != null) {

            return new EventResponse(null, updatedAccount);
        }

        return null;
    }

    public EventResponse Deposit(EventRequest eventRequest){

        Account account = accountRepository.getById(eventRequest.getDestination());

        if (account == null){
            
            account = createNewAccount(eventRequest.getDestination(), String.valueOf(eventRequest.getAmount()));

            return new EventResponse(null, account );
        }

        double newBalance = account.getBalance() + eventRequest.getAmount();
        account.setBalance(newBalance);

        Account updatedAccount = accountRepository.updateAccount(account);
        
        if (updatedAccount != null) {
            return new EventResponse(null, updatedAccount);
        }
        return new EventResponse();

    }

    public Account createNewAccount(String id, String balance){
        return accountRepository.addNewAccount(id, balance);
    }

    public String getBalance (String id){

        Account account = accountRepository.getById(id);

        if (account == null)
            return "0";

        return ""+ account.getBalance();

    }    
}
