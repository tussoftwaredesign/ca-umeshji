package com.tus.ca.flight.reservation.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Invoice {
    private final String invoiceNumber;
    private final LocalDate issueDate;
    private final String customerName;
    private final BigDecimal totalAmount;
    private final List<InvoiceItem> invoiceItems;

    public Invoice(String invoiceNumber, LocalDate issueDate, String customerName,
                   BigDecimal totalAmount, List<InvoiceItem> invoiceItems) {
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.invoiceItems = new ArrayList<>(invoiceItems);
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return Collections.unmodifiableList(invoiceItems);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceNumber, invoice.invoiceNumber) && Objects.equals(issueDate, invoice.issueDate) && Objects.equals(customerName, invoice.customerName) && Objects.equals(totalAmount, invoice.totalAmount) && Objects.equals(invoiceItems, invoice.invoiceItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceNumber, issueDate, customerName, totalAmount, invoiceItems);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", issueDate=" + issueDate +
                ", customerName='" + customerName + '\'' +
                ", totalAmount=" + totalAmount +
                ", lineItems=" + invoiceItems +
                '}';
    }

    public static final class InvoiceItem {
        private final int quantity;
        private final BigDecimal unitPrice;

        public InvoiceItem(int quantity, BigDecimal unitPrice) {
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            InvoiceItem that = (InvoiceItem) o;
            return quantity == that.quantity && Objects.equals(unitPrice, that.unitPrice);
        }

        @Override
        public int hashCode() {
            return Objects.hash(quantity, unitPrice);
        }

        @Override
        public String toString() {
            return "InvoiceItem{" +
                    ", quantity=" + quantity +
                    ", unitPrice=" + unitPrice +
                    '}';
        }
    }


}
