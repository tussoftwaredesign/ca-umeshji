package com.tus.ca.flight.reservation.service;

import java.math.BigDecimal;

public class Payment {

    public void process(BigDecimal amount, String currency) {
        System.out.println("Processing payment in Payment (super class)");
    }
}
