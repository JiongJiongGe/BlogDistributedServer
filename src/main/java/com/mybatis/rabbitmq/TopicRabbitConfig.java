package com.mybatis.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yunkai on 2017/7/12.
 */
@Configuration
public class TopicRabbitConfig {

    public final static String TOPIC_ONE = "topic.one";

    public final static String TOPIC_EXCHANGE = "topicExchange";

    @Bean
    public Queue queue(){
        return new Queue(TOPIC_ONE);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeOne(Queue queue_one, TopicExchange exchange){
        return BindingBuilder.bind(queue_one).to(exchange).with("topic.#");
    }

    //功能类似与@RabbitLister注视
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             QueueConsumer consume) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(queue());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(consume);
        //如果消息没有应答，消息进入消费者的最多条数，此处为2条
        container.setPrefetchCount(2);
        return container;
    }

}
