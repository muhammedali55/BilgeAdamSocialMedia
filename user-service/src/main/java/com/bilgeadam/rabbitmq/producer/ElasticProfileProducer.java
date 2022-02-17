package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.Notification;
import com.bilgeadam.rabbitmq.model.ProfileNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElasticProfileProducer {

    private final RabbitTemplate   rabbitTemplate;

    public void sendMessageProfileSave(ProfileNotification notification) {
        rabbitTemplate.convertAndSend("bilgeadam.exchange","elastic-key-profile-save",notification);

    }
}
