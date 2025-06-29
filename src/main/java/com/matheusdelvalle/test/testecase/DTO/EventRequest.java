package com.matheusdelvalle.test.testecase.DTO;

import com.matheusdelvalle.test.testecase.emums.EventEnum;

public class EventRequest {

    // {"type":"deposit", "destination":"100", "amount":10}

    private EventEnum type;
    private String destination;
    private long amount;
    
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
    public EventEnum getType() {
        return type;
    }
    public void setType(EventEnum type) {
        this.type = type;
    }
    

}
