package by.aliyeva.zenart.masterclass;

import jakarta.validation.constraints.NotNull;

public record MasterclassPurchaseRequest(
        @NotNull(message = "Masterclass is mandatory")
        Integer masterclassId,
        @NotNull(message = "Quantity is mandatory")
        Integer quantity
) {
}
