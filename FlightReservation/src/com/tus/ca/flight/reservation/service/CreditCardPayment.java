package com.tus.ca.flight.reservation.service;

import java.math.BigDecimal;

public class CreditCardPayment extends Payment {

    public void process(BigDecimal amount, String currency) {
        super.process(amount,currency);
        System.out.println("Processing credit card payment");
    }
}
