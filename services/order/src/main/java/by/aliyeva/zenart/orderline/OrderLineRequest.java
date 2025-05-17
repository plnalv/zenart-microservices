package by.aliyeva.zenart.orderline;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer masterclassId,
        double quantity
) {
}
