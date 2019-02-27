package com.lambdaschool.cities;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CitiesApplication {

    static final String EXCHANGE_NAME = "LambdaServer";
    static final String QUEUE_CITIES_1 = "Cities1";
    static final String QUEUE_CITIES_2 = "Cities2";
    static final String QUEUE_SECRET = "SecretQueue";

    public static void main(String[] args) {
        SpringApplication.run(CitiesApplication.class, args);
    }

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue appQueueCities1() {
        return new Queue(QUEUE_CITIES_1);
    }

    public Binding declareBindingCities1() {
        return BindingBuilder.bind(appQueueCities1()).to(appExchange()).with(QUEUE_CITIES_1);
    }

    @Bean
    public Queue appQueueCities2() {
        return new Queue(QUEUE_CITIES_2);
    }

    public Binding declareBindingCities2() {
        return BindingBuilder.bind(appQueueCities2()).to(appExchange()).with(QUEUE_CITIES_2);
    }

    @Bean
    public Queue appQueueSecret() {
        return new Queue(QUEUE_SECRET);
    }

    public Binding declareBindingSecret() {
        return BindingBuilder.bind(appQueueSecret()).to(appExchange()).with(QUEUE_SECRET);
    }


    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}


