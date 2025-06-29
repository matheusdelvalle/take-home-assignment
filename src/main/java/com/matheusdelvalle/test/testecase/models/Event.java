package com.matheusdelvalle.test.testecase.models;

import com.matheusdelvalle.test.testecase.emums.EventEnum;

public class Event {
    
private EventEnum type;
private Float amount;
private long origin;
private long destination;

public long getDestination() {
    return destination;
}

public void setDestination(long destination) {
    this.destination = destination;
}

public Float getAmount() {
    return amount;
}

public void setAmount(Float amount) {
    this.amount = amount;
}

public long getOrigin() {
    return origin;
}

public void setOrigin(long origin) {
    this.origin = origin;
}

public EventEnum getType() {
    return type;
}

public void setType(EventEnum type) {
    this.type = type;
}

}
