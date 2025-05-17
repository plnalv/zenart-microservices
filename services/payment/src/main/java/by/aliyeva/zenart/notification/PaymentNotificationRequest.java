package by.aliyeva.zenart.notification;

import by.aliyeva.zenart.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String userFirstName,
        String userLastName,
        String userEmail
) {
}
