package com.suse.travelsales.spring.api;

import com.suse.travelsales.City;
import com.suse.travelsales.Tour;
import com.suse.travelsales.TourCalculator;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class TravelingSalesman {

    static Logger logger = Logger.getLogger(TravelingSalesman.class.getName());

    @PostMapping(path="/permutations")
    public List<List<City>> getPerumutations(@RequestBody List<City> cities) {
        TourCalculator calc = new TourCalculator(cities);
        List<List<City>> result = calc.getPerumtations();
        logger.info("Calculated " + result.size() + " permutations.");
        return result;
    }

    @PostMapping(path="/distances")
    public List<List<Tour>> getDistances(@RequestBody List<City> cities) {
        TourCalculator calc = new TourCalculator(cities);
        List<List<Tour>> tours = calc.getSortedTours();
        logger.info("Calculated " + tours.size() + " unique sets of distances.");
        return tours;
    }

    @PostMapping(path="/tour")
    public List<Tour> getShortest(@RequestBody List<City> cities) {
        TourCalculator calc = new TourCalculator(cities);
        List<Tour> tours = calc.getShortest();
        logger.info("Shortest distance has " + tours.size() + " unique paths.");
        return tours;
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
