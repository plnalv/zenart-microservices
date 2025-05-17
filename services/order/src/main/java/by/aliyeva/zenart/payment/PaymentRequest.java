package by.aliyeva.zenart.payment;

import by.aliyeva.zenart.order.PaymentMethod;
import by.aliyeva.zenart.user.UserResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        UserResponse user
) {
}
