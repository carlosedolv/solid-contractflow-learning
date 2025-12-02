package com.carlosedolv.contractflow.entities.enums;

public enum PaymentType {
    CREDIT("credit"),
    DEBIT("debit"),
    PIX("pix");

    private String method;

    PaymentType(String method) {
        this.method = method;
    }

    public String getValue() {
        return method;
    }

    public static PaymentType fromCode(String method) {
        for (PaymentType type : PaymentType.values()) {
            if (type.method.equalsIgnoreCase(method)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid payment type method: " + method);
    }
}
