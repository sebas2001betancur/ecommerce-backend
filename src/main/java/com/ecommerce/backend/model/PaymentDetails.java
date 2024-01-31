package com.ecommerce.backend.model;

public class PaymentDetails {

    private String paymentMethod;
    private String status;
    private String paymentId;
    private String mercadoPagoPaymentLinkId;
    private String mercadoPagoPaymentLinkReferenceId;
    private String mercadoPagoPaymentLinkStatus;
    private String mercadoPagoPaymentId;

    public PaymentDetails() {
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getMercadoPagoPaymentLinkId() {
        return mercadoPagoPaymentLinkId;
    }

    public void setMercadoPagoPaymentLinkId(String mercadoPagoPaymentLinkId) {
        this.mercadoPagoPaymentLinkId = mercadoPagoPaymentLinkId;
    }

    public String getMercadoPagoPaymentLinkReferenceId() {
        return mercadoPagoPaymentLinkReferenceId;
    }

    public void setMercadoPagoPaymentLinkReferenceId(String mercadoPagoPaymentLinkReferenceId) {
        this.mercadoPagoPaymentLinkReferenceId = mercadoPagoPaymentLinkReferenceId;
    }

    public String getMercadoPagoPaymentLinkStatus() {
        return mercadoPagoPaymentLinkStatus;
    }

    public void setMercadoPagoPaymentLinkStatus(String mercadoPagoPaymentLinkStatus) {
        this.mercadoPagoPaymentLinkStatus = mercadoPagoPaymentLinkStatus;
    }

    public String getMercadoPagoPaymentId() {
        return mercadoPagoPaymentId;
    }

    public void setMercadoPagoPaymentId(String mercadoPagoPaymentId) {
        this.mercadoPagoPaymentId = mercadoPagoPaymentId;
    }
}
