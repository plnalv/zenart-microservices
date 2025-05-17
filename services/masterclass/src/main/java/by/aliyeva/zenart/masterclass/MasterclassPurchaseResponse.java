package by.aliyeva.zenart.masterclass;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record MasterclassPurchaseResponse(
        Integer masterclassId,
        String name,
        String description,
        LocalDateTime date,
        LocalTime time,
        BigDecimal price,
        Integer quantity
) {
}
