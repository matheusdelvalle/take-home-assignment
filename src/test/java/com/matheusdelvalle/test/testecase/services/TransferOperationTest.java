package com.matheusdelvalle.test.testecase.services;

import com.matheusdelvalle.test.testecase.DTO.EventRequest;
import com.matheusdelvalle.test.testecase.DTO.EventResponse;
import com.matheusdelvalle.test.testecase.emums.Operation;
import com.matheusdelvalle.test.testecase.models.Account;
import com.matheusdelvalle.test.testecase.repositories.AccountMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


class TransferOperationTest {

    @Mock
    private AccountMemoryRepository accountRepository;

    @InjectMocks
    private TransferOperation transferOperation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_SuccessfulTransfer() {
        String originId = "1";
        String destinationId = "2";
        int amount = 50;

        Account origin = new Account(originId, 100);
        Account destination = new Account(destinationId, 30);

        EventRequest request = new EventRequest();
        request.setOrigin(originId);
        request.setDestination(destinationId);
        request.setAmount(amount);

        when(accountRepository.getById(originId)).thenReturn(origin);
        when(accountRepository.getById(destinationId)).thenReturn(destination);

        when(accountRepository.updateAccount(any(Account.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EventResponse response = transferOperation.execute(request);

        assertNotNull(response);
        assertEquals(50, response.getOrigin().getBalance());
        assertEquals(80, response.getDestination().getBalance());
    }

    @Test
    void testExecute_OriginAccountDoesNotExist() {
        String originId = "1";
        String destinationId = "2";
        int amount = 50;

        EventRequest request = new EventRequest();
        request.setOrigin(originId);
        request.setDestination(destinationId);
        request.setAmount(amount);

        when(accountRepository.getById(originId)).thenReturn(null);

        EventResponse response = transferOperation.execute(request);

        assertNull(response);
    }

    @Test
    void testExecute_DestinationAccountDoesNotExist() {
        String originId = "1";
        String destinationId = "2";
        int amount = 20;

        Account origin = new Account(originId, 100);
        Account newDestination = new Account(destinationId, 0);

        EventRequest request = new EventRequest();
        request.setOrigin(originId);
        request.setDestination(destinationId);
        request.setAmount(amount);

        when(accountRepository.getById(originId)).thenReturn(origin);
        when(accountRepository.getById(destinationId)).thenReturn(null);
        when(accountRepository.addNewAccount(eq(destinationId), anyString())).thenReturn(newDestination);
        when(accountRepository.updateAccount(any(Account.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EventResponse response = transferOperation.execute(request);

        assertNotNull(response);
        assertEquals(80, response.getOrigin().getBalance());
        assertEquals(20, response.getDestination().getBalance());
    }

    @Test
    void testExecute_UpdateAccountFails() {
        String originId = "1";
        String destinationId = "2";
        int amount = 10;

        Account origin = new Account(originId, 50);
        Account destination = new Account(destinationId, 10);

        EventRequest request = new EventRequest();
        request.setOrigin(originId);
        request.setDestination(destinationId);
        request.setAmount(amount);

        when(accountRepository.getById(originId)).thenReturn(origin);
        when(accountRepository.getById(destinationId)).thenReturn(destination);
        when(accountRepository.updateAccount(origin)).thenReturn(null);
        when(accountRepository.updateAccount(destination)).thenReturn(destination);

        EventResponse response = transferOperation.execute(request);

        assertNull(response);
    }

    @Test
    void testGetOperation_ReturnsTransferEnum() {
        assertEquals(Operation.TRANSFER, transferOperation.getOperation());
    }
}