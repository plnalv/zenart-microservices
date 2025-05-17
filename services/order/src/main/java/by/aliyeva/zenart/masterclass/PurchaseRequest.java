package by.aliyeva.zenart.masterclass;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Masterclass is mandatory")
        Integer masterclassId,
        @Positive(message = "Quantity is mandatory")
        double quantity
) {
}
