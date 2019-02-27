package com.lambdaschool.cities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping(value="/cities")
public class CityController {

    private final CityRepository cityrepos;
    private final RabbitTemplate rt;

    public CityController(CityRepository cityrepos, RabbitTemplate rt) {
        this.cityrepos = cityrepos;
        this.rt = rt;
    }

    @GetMapping("/afford")
    public void getAfford() {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityrepos.findAll());
        for (City c: cities) {
            int rand = new Random().nextInt(10);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);

            log.info("Sending Message...");
            if (randBool) {
                rt.convertAndSend(CitiesApplication.QUEUE_SECRET, message);
            } else if (c.getAffordabilityIndex() < 6 ) {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES_1, message);
            } else {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES_2, message);
            }
        }
    }

    @GetMapping("/homes")
    public void getHomes() {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityrepos.findAll());
        for (City c: cities) {
            int rand = new Random().nextInt(20);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);

            log.info("Sending Message...");
            if (randBool) {
                rt.convertAndSend(CitiesApplication.QUEUE_SECRET, message);
            } else if (c.getMedianValue() > 200000 ) {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES_1, message);
            } else {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES_2, message);
            }
        }
    }

    @GetMapping("/names")
    public void getNames() {
        ArrayList<City> cities = new ArrayList<City>();
        cities.addAll(cityrepos.findAll());
        for (City c: cities) {
            int rand = new Random().nextInt(20);
            boolean randBool = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), rand, randBool);

            log.info("Sending Message...");
            if (randBool) {
                rt.convertAndSend(CitiesApplication.QUEUE_SECRET, message);
            } else  {
                rt.convertAndSend(CitiesApplication.QUEUE_CITIES_1, message);
            }
        }
    }
}


//        /cities/homes ->
//
//        put all secret messages on the secret queue
//        put all NON secret messages with home prices > 200000 in the cities1 queue
//        put all other messages in the cities2 queue
//        /cities/names ->
//
//        put all secret messages on the secret queue
//        put all NON secret messages the cities1 queue
//        put nothing in the cities2 queue