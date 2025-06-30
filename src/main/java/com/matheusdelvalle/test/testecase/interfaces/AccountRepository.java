package com.matheusdelvalle.test.testecase.interfaces;

import com.matheusdelvalle.test.testecase.models.Account;

public interface AccountRepository {
    public Account getById(String id);
    public void reset();
    public Account addNewAccount(String id, String balance);
    public Account updateAccount(Account account);
}
