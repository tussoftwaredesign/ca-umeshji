package com.tus.ca.flight.reservation.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record PaymentMethod(String paymentId, BigDecimal amount, String currency, LocalDateTime timestamp) {
    public PaymentMethod {
        Objects.requireNonNull(paymentId, "Payment ID cannot be null");
        Objects.requireNonNull(amount, "Amount cannot be null");
        Objects.requireNonNull(currency, "Currency cannot be null");
        Objects.requireNonNull(timestamp, "Timestamp cannot be null");

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}