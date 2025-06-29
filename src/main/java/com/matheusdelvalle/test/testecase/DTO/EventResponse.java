package com.matheusdelvalle.test.testecase.DTO;

import com.matheusdelvalle.test.testecase.models.Account;

public class EventResponse {
    
// {"origin": {"id":"100", "balance":0}, "destination": {"id":"300", "balance":15}}

    private Account origin;
    private Account destination;

    public EventResponse(Account origin, Account destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public EventResponse() {
        this.origin = null;
        this.destination = null;
    }

    public Account getOrigin() {
        return origin;
    }
    public void setOrigin(Account origin) {
        this.origin = origin;
    }
    
    public Account getDestination() {
        return destination;
    }
    public void setDestination(Account destination) {
        this.destination = destination;
    }
}
