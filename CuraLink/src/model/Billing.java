package model;

import java.io.Serializable;

public class Billing implements Serializable {
    private static final long serialVersionUID = 1L;

    private String patientUsername;
    private double amount;
    private String status; // Paid or Unpaid
    private String paymentMethod; // Insurance, Credit Card, etc.
    private String dateIssued;
    private String datePaid;
    private String insuranceProvider;

    public Billing(String patientUsername, double amount, String dateIssued) {
        this.patientUsername = patientUsername;
        this.amount = amount;
        this.dateIssued = dateIssued;
        this.status = "Unpaid";
        this.paymentMethod = "";
        this.datePaid = "";
        this.insuranceProvider = "";
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void markPaid(String paymentMethod, String insuranceProvider, String datePaid) {
        this.status = "Paid";
        this.paymentMethod = paymentMethod;
        this.insuranceProvider = insuranceProvider;
        this.datePaid = datePaid;
    }
}
