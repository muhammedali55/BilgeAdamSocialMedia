package com.bilgeadam.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {

    /**
     * Exchange name verelim
     */
    private String excahangeName= "bilgeadam.exchange";
    /**
     * RoutingKey ve QueueName verelim bunlar eşleşecekler
     */
    private String routingKeyCreateUser = "routingKeyCreateUSer";
    private String queueNameCreateUser = "queueCreateUser";

    private String routingKeyDeleteUser = "routingKeyDeleteUser";
    private String queueNameDeleteUser = "queueDeleteUser";
    @Bean
    Queue queue(){
        return new Queue(queueNameCreateUser);
    }

    @Bean
    Queue queueDelete(){
        return new Queue(queueNameDeleteUser);
    }


    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(excahangeName);
    }

    @Bean
    public Binding binding(final Queue queue,final DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(routingKeyCreateUser);
    }

    @Bean
    public Binding bindingDelete(final Queue queueDelete,final DirectExchange directExchange){
        return BindingBuilder.bind(queueDelete).to(directExchange).with(routingKeyDeleteUser);
    }
}
