package by.aliyeva.zenart.kafka;

import by.aliyeva.zenart.email.EmailService;
import by.aliyeva.zenart.kafka.order.OrderConfirmation;
import by.aliyeva.zenart.kafka.payment.PaymentConfirmation;
import by.aliyeva.zenart.notification.Notification;
import by.aliyeva.zenart.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static by.aliyeva.zenart.notification.NotificationType.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic", groupId = "paymentGroup")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        // send email
        var userName = paymentConfirmation.userFirstName() + " " + paymentConfirmation.userLastName();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.userEmail(),
                userName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic", groupId = "orderGroup")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        // send email
        var userName = orderConfirmation.user().firstName() + " " + orderConfirmation.user().lastName();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.user().email(),
                userName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.masterclasses()
        );
    }
}
