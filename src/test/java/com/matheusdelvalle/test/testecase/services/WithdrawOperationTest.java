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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WithdrawOperationTest {

    @Mock
    private AccountMemoryRepository accountRepository;

    @InjectMocks
    private WithdrawOperation withdrawOperation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_WithValidAccount_ShouldWithdrawAndReturnResponse() {
        String originId = "123";
        int initialBalance = 1000;
        int withdrawAmount = 200;

        Account account = new Account();
        account.setId(originId);
        account.setBalance(initialBalance);

        EventRequest request = mock(EventRequest.class);
        when(request.getOrigin()).thenReturn(originId);
        when(request.getAmount()).thenReturn(withdrawAmount);

        when(accountRepository.getById(originId)).thenReturn(account);

        Account updatedAccount = new Account();
        updatedAccount.setId(originId);
        updatedAccount.setBalance(initialBalance - withdrawAmount);

        when(accountRepository.updateAccount(any(Account.class))).thenReturn(updatedAccount);

        EventResponse response = withdrawOperation.execute(request);

        assertNotNull(response);
        assertNotNull(response.getOrigin());
        assertEquals(originId, response.getOrigin().getId());
        assertEquals(initialBalance - withdrawAmount, response.getOrigin().getBalance());
        assertNull(response.getDestination());

        // Verify that accountRepository.updateAccount was called with the correct new balance
        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).updateAccount(captor.capture());
        assertEquals(initialBalance - withdrawAmount, captor.getValue().getBalance());
    }

    @Test
    void testExecute_AccountNotFound_ShouldReturnNull() {
        String originId = "not_found";
        EventRequest request = mock(EventRequest.class);
        when(request.getOrigin()).thenReturn(originId);

        when(accountRepository.getById(originId)).thenReturn(null);

        EventResponse response = withdrawOperation.execute(request);

        assertNull(response);
        verify(accountRepository, never()).updateAccount(any());
    }

    @Test
    void testExecute_UpdateAccountReturnsNull_ShouldReturnNull() {
        String originId = "123";
        int initialBalance = 1000;
        int withdrawAmount = 500;

        Account account = new Account();
        account.setId(originId);
        account.setBalance(initialBalance);

        EventRequest request = mock(EventRequest.class);
        when(request.getOrigin()).thenReturn(originId);
        when(request.getAmount()).thenReturn(withdrawAmount);

        when(accountRepository.getById(originId)).thenReturn(account);
        when(accountRepository.updateAccount(any(Account.class))).thenReturn(null);

        EventResponse response = withdrawOperation.execute(request);

        assertNull(response);
    }

    @Test
    void testGetOperation_ShouldReturnWithdrawEnum() {
        assertEquals(Operation.WITHDRAW, withdrawOperation.getOperation());
    }
}