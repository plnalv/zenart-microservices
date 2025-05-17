package by.aliyeva.zenart.order;

import by.aliyeva.zenart.masterclass.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotEmpty(message = "User should be present")
        @NotBlank(message = "User should be present")
        @NotNull(message = "User should be present")
        String userId,
        @NotEmpty(message = "You should at least purchase one masterclass")
        List<PurchaseRequest> masterclasses
) {
}
