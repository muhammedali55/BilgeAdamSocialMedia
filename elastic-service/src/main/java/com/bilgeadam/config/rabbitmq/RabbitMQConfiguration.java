package com.bilgeadam.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration  {

    private String excahangeName= "bilgeadam.exchange";

    private String routingKey = "elastic-key-profile-save";
    private String queueProfileSave = "profile-save-queue";

    @Bean
    Queue queue(){
        return new Queue(queueProfileSave);
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(excahangeName);
    }
    @Bean
    Binding bindingProfileSave(final Queue queue, final DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(routingKey);
    }
}
