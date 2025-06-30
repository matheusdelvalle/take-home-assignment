package com.matheusdelvalle.test.testecase.services;

import com.matheusdelvalle.test.testecase.DTO.EventRequest;
import com.matheusdelvalle.test.testecase.DTO.EventResponse;
import com.matheusdelvalle.test.testecase.emums.EventEnum;

public interface AccountOperation {
    public EventResponse execute(EventRequest eventRequest);
    public EventEnum getOperation();
    
}
