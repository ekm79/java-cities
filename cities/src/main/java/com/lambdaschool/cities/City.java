package com.lambdaschool.cities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class City {

    private @Id @GeneratedValue Long id;
    private String cityName;
    private long medianValue;
    private int affordabilityIndex;

    public City() {
        //default constructor
    }

    public City(String cityName, long medianValue, int affordabilityIndex) {
        this.cityName = cityName;
        this.medianValue = medianValue;
        this.affordabilityIndex = affordabilityIndex;
    }

}
