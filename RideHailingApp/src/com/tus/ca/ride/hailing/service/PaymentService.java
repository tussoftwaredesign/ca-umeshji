package com.tus.ca.ride.hailing.service;

import com.tus.ca.ride.hailing.enums.PaymentStatus;
import com.tus.ca.ride.hailing.model.Payment;

public class PaymentService {
    public void processPayment(Payment payment) {
        if (payment.getAmount() > 0) {
            payment.setStatus(PaymentStatus.COMPLETED);
            System.out.println("Payment processed successfully: " + payment);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
            throw new IllegalArgumentException("Invalid payment amount: " + payment.getAmount());
        }
    }
}
