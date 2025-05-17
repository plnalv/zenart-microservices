package by.aliyeva.zenart.kafka;

import by.aliyeva.zenart.masterclass.PurchaseResponse;
import by.aliyeva.zenart.order.PaymentMethod;
import by.aliyeva.zenart.user.UserResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        UserResponse user,
        List<PurchaseResponse> masterclasses
) {
}
