package com.tus.ca.flight.reservation.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditCardPayment extends Payment {

    public void process(BigDecimal amount, String currency) {  //example of polymorphism feature (method overriding)
        amount =  amount.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Rounding up amount " + amount); // example of pass by value feature
        super.process(amount,currency);  // example of super (reference variable used to call immediate parent (Payment) of current class (Credit Card Payment)
        System.out.println("Processing credit card payment");
    }
}
