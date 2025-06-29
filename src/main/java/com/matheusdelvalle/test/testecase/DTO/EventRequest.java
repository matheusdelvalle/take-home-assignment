package com.matheusdelvalle.test.testecase.DTO;

import com.matheusdelvalle.test.testecase.emums.EventEnum;

public class EventRequest {

    private EventEnum type;
    private long amount;
    private String destination;
    private String origin;
    
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
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
