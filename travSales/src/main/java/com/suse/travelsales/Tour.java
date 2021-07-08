package com.suse.travelsales;

import java.util.ArrayList;
import java.util.List;

public class Tour {
    private float distance = 0.0F;
    List<City> cities = new ArrayList<>();

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder().append(" {distance: ")
                .append(this.getDistance())
                .append(" cities: ")
                .append(cities);
        return builder.toString();
    }

}
