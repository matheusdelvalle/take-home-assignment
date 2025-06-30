package com.matheusdelvalle.test.testecase.repositories;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.matheusdelvalle.test.testecase.interfaces.AccountRepository;
import com.matheusdelvalle.test.testecase.models.Account;

@Repository
public class AccountMemoryRepository implements AccountRepository {

    private final ConcurrentHashMap<String, String> inMemoryStore = new ConcurrentHashMap<>();

    @Override
    public Account getById(String id) {

        String balance =  inMemoryStore.get(""+id);
        
        if(balance == null)
            return null;

        Account account = new Account(id, Integer.valueOf(balance));

        return account;
    }
    
    @Override
    public void reset() {
        inMemoryStore.clear();
    }

    @Override
    public Account addNewAccount(String id, String balance) {
        
        inMemoryStore.put(id, balance);

        return new Account(id, Integer.valueOf(balance));
    }

    @Override
    public Account updateAccount(Account account) {

        return inMemoryStore.computeIfPresent(String.valueOf(account.getId()), (k, v) -> {
            return String.valueOf(account.getBalance());
        }) != null ? account : null;
    }

}
