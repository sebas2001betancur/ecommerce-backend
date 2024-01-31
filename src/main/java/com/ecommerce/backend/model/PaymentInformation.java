package com.ecommerce.backend.model;

import jakarta.persistence.Column;

public class PaymentInformation {

    @Column(name = "cardholder_name")
    private String cardholderName;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration_Month")
    private String expirationMonth;

    @Column(name = "expiration_year")
    private String expirationYear;
    @Column(name = "token")
    private String token;
    @Column(name = "cvc")
    private String cvc;
    @Column(name = "username")
    private String username;
    @Column(name = "success")
    private boolean success;

    public PaymentInformation() {
    }

    public PaymentInformation(String cardholderName, String cardNumber, String expirationMonth, String expirationYear, String token, String cvc, String username, boolean success) {
        this.cardholderName = cardholderName;
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.token = token;
        this.cvc = cvc;
        this.username = username;
        this.success = success;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
