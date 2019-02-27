package com.lambdaschool.cities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CityMessageListener {

    @RabbitListener(queues = CitiesApplication.QUEUE_SECRET)
    public void receiveSecretMessage(CityMessage cm) {
        log.info("Received Message: {} ", cm.toString());
    }

    @RabbitListener(queues = CitiesApplication.QUEUE_CITIES_1)
    public void receiveCities1(CityMessage cm) {
        log.info("Received Message: {}", cm.toString());
    }

    @RabbitListener(queues = CitiesApplication.QUEUE_CITIES_2)
    public void receiveCities2(CityMessage cm) {
        log.info("Received Message: {}", cm.toString());
    }
}
