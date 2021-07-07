package com.suse.travelsales.spring.api;

import com.suse.travelsales.City;
import com.suse.travelsales.Tour;
import com.suse.travelsales.TourCalculator;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TravelingSalesman {
    @PostMapping(path="/permutations")
    public List<List<City>> getPerumutations(@RequestBody List<City> cities) {
        TourCalculator calc = new TourCalculator(cities);
        return calc.getPerumtations();
    }

    @PostMapping(path="/distances")
    public List<List<Tour>> getDistances(@RequestBody List<City> cities) {
        TourCalculator calc = new TourCalculator(cities);
        return calc.getSortedTours();
    }

    @PostMapping(path="/tour")
    public List<Tour> getShortest(@RequestBody List<City> cities) {
        TourCalculator calc = new TourCalculator(cities);
        return calc.getShortest();
    }

    @GetMapping(path="/random", produces = "application/json")
    public List<City> getRandomList() {
        int number = 5;
        List<City> cities = new ArrayList<City>();
        for(int i=0; i<number; i++) {
            cities.add(new City());
        }
        return cities;
    }


}
