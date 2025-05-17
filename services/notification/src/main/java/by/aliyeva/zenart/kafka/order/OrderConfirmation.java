package by.aliyeva.zenart.kafka.order;

import by.aliyeva.zenart.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        User user,
        List<Masterclass> masterclasses
) {
}
