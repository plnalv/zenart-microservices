package by.aliyeva.zenart.masterclass;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record MasterclassRequest (
    Integer id,
    @NotNull(message = "Masterclass name is required")
    String name,
    @NotNull(message = "Masterclass description is required")
    String description,
    @NotNull(message = "Masterclass date is required")
    LocalDateTime date,
    @NotNull(message = "Masterclass time is required")
    LocalTime time,
    @Positive(message = "Seats quantity should be positive")
    Integer seatsQuantity,
    @Positive(message = "Price should be positive")
    BigDecimal price,
    @NotNull(message = "Masterclass category is required")
    Integer categoryId
) {

}
