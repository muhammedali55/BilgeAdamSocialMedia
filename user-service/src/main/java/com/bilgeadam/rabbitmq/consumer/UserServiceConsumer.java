package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceConsumer {

    @RabbitListener(queues = "queueCreateUser")
    public void consumeNotification(Notification notification){
       log.info("Bildirim geldi....: "+ notification.toString());
    }

    @RabbitListener(queues = "queueDeleteUser")
    public void consumeDeleteUser(Notification notification){
        log.info("Kullanı Silindi....: "+ notification.toString());
    }
}
