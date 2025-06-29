package com.matheusdelvalle.test.testecase.interfaces;

import com.matheusdelvalle.test.testecase.models.Account;

public interface RepositoryInterface {
    public Account getById(String id);
    public void reset();
    public Long getNextId();
    public Account addNewAccount(String id, String balance);
    public Account updateAccount(Account account);
}
