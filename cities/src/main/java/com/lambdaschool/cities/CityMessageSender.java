package com.lambdaschool.cities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@Service
public class CityMessageSender {
    private final RabbitTemplate rt;
    private final CityRepository cityrepos;

    public CityMessageSender(RabbitTemplate rt, CityRepository cityrepos) {
        this.rt = rt;
        this.cityrepos = cityrepos;
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendMessage() {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityrepos.findAll());

        for (City c: cities) {
            int rand = new Random().nextInt(10);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);

            if (randBool) {
                rt.convertAndSend(CitiesApplication.QUEUE_SECRET, message);
            } else if (c.getAffordabilityIndex() < 6 ) {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES_1, message);
            } else {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES_2, message);
            }
        }
    }
}
