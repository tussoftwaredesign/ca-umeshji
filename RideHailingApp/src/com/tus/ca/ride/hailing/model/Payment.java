package com.tus.ca.ride.hailing.model;

import com.tus.ca.ride.hailing.enums.PaymentStatus;

import java.util.Objects;

public class Payment {
    private final String paymentId;
    private final String userId;
    private final String driverId;
    private final double amount;
    private PaymentStatus status;

    public Payment(String paymentId, String userId, String driverId, double amount, PaymentStatus status) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.driverId = driverId;
        this.amount = amount;
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getUserId() {
        return userId;
    }

    public String getDriverId() {
        return driverId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(amount, payment.amount) == 0 && Objects.equals(paymentId, payment.paymentId) && Objects.equals(userId, payment.userId) && Objects.equals(driverId, payment.driverId) && status == payment.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, userId, driverId, amount, status);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", userId='" + userId + '\'' +
                ", driverId='" + driverId + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
