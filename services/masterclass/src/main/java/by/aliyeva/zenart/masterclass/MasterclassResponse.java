package by.aliyeva.zenart.masterclass;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record MasterclassResponse(
    Integer id,
    String name,
    String description,
    LocalDateTime date,
    LocalTime time,
    Integer seatsQuantity,
    BigDecimal price,
    Integer categoryId,
    String categoryName,
    String categoryDescription
) {
}
