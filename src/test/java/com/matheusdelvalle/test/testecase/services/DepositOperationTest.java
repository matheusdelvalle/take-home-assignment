package com.matheusdelvalle.test.testecase.services;

import com.matheusdelvalle.test.testecase.DTO.EventRequest;
import com.matheusdelvalle.test.testecase.DTO.EventResponse;
import com.matheusdelvalle.test.testecase.emums.Operation;
import com.matheusdelvalle.test.testecase.models.Account;
import com.matheusdelvalle.test.testecase.repositories.AccountMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class DepositOperationTest {

    @Mock
    private AccountMemoryRepository accountRepository;

    @InjectMocks
    private DepositOperation depositOperation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_shouldCreateNewAccountIfNotExists() {
        String destination = "123";
        int amount = 100;
        EventRequest request = new EventRequest();
        // Assuming setters or constructor exist
        request.setDestination(destination);
        request.setAmount(amount);

        when(accountRepository.getById(destination)).thenReturn(null);

        Account newAccount = new Account();
        newAccount.setId(destination);
        newAccount.setBalance(amount);
        when(accountRepository.addNewAccount(destination, String.valueOf(amount))).thenReturn(newAccount);

        EventResponse response = depositOperation.execute(request);

        assertNull(response.getOrigin());
        assertNotNull(response.getDestination());
        assertEquals(destination, response.getDestination().getId());
        assertEquals(amount, response.getDestination().getBalance());
        verify(accountRepository).addNewAccount(destination, String.valueOf(amount));
    }

    @Test
    void execute_shouldDepositToExistingAccount() {
        String destination = "456";
        int initialBalance = 50;
        int depositAmount = 70;
        EventRequest request = new EventRequest();
        request.setDestination(destination);
        request.setAmount(depositAmount);

        Account existingAccount = new Account();
        existingAccount.setId(destination);
        existingAccount.setBalance(initialBalance);

        when(accountRepository.getById(destination)).thenReturn(existingAccount);

        Account updatedAccount = new Account();
        updatedAccount.setId(destination);
        updatedAccount.setBalance(initialBalance + depositAmount);

        when(accountRepository.updateAccount(any(Account.class))).thenReturn(updatedAccount);

        EventResponse response = depositOperation.execute(request);

        assertNull(response.getOrigin());
        assertNotNull(response.getDestination());
        assertEquals(destination, response.getDestination().getId());
        assertEquals(initialBalance + depositAmount, response.getDestination().getBalance());

        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).updateAccount(accountCaptor.capture());
        assertEquals(initialBalance + depositAmount, accountCaptor.getValue().getBalance());
    }

    @Test
    void getOperation_shouldReturnDepositEnum() {
        assertEquals(Operation.deposit, depositOperation.getOperation());
    }
}