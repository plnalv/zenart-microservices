package by.aliyeva.zenart.kafka.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record Masterclass(
        Integer masterclassId,
        String name,
        String description,
        LocalDateTime date,
        LocalTime time,
        BigDecimal price,
        double quantity
) {
}
