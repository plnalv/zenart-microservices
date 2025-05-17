package by.aliyeva.zenart.order;

import by.aliyeva.zenart.exception.BusinessException;
import by.aliyeva.zenart.kafka.OrderConfirmation;
import by.aliyeva.zenart.kafka.OrderProducer;
import by.aliyeva.zenart.masterclass.MasterclassClient;
import by.aliyeva.zenart.masterclass.PurchaseRequest;
import by.aliyeva.zenart.orderline.OrderLineRequest;
import by.aliyeva.zenart.orderline.OrderLineService;
import by.aliyeva.zenart.payment.PaymentClient;
import by.aliyeva.zenart.payment.PaymentRequest;
import by.aliyeva.zenart.user.UserClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final UserClient userClient;
    private final MasterclassClient masterclassClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createdOrder(OrderRequest request) {

        // check the user --> OpenFeign
        var user = this.userClient.findUserById(request.userId())
                .orElseThrow(() -> new BusinessException("Cannot create an order:: No user exists with the provided id"));

        // purchase the masterclass --> masterclass-ms (RestTemplate)
        var purchasedMasterclasses = this.masterclassClient.purchaseMasterclasses(request.masterclasses());

        // persist order
        var order = this.repository.save(mapper.toOrder(request));

        // persist order lines
        for (PurchaseRequest purchaseRequest: request.masterclasses()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.masterclassId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // start payment process
        var paymentRequest = new PaymentRequest(
          request.amount(),
          request.paymentMethod(),
          order.getId(),
          order.getReference(),
          user
        );
        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirm  --> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        user,
                        purchasedMasterclasses
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id %d not found", orderId)));
    }
}
