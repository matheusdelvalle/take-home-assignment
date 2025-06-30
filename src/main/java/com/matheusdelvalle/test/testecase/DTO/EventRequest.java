package com.matheusdelvalle.test.testecase.DTO;

import com.matheusdelvalle.test.testecase.emums.Operation;

public class EventRequest {

    private Operation type;
    private int amount;
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
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public Operation getType() {
        return type;
    }
    public void setType(Operation type) {
        this.type = type;
    }
    

}
