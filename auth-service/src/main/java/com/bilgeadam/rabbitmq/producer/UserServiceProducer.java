package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Notification notification) {
        rabbitTemplate.convertAndSend("bilgeadam.exchange","routingKeyCreateUSer",notification);
        System.out.println("notification = " + notification.toString());
    }

    public void deleteUser(Notification notification) {
        rabbitTemplate.convertAndSend("bilgeadam.exchange","routingKeyDeleteUser",notification);
        System.out.println("notification = " + notification.toString());
    }
}
