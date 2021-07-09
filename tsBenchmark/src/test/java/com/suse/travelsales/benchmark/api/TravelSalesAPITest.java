package com.suse.travelsales.benchmark.api;

import com.suse.travelsales.City;
import com.suse.travelsales.Tour;
import com.suse.travelsales.benchmark.api.TravelSalesAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TravelSalesAPITest {
    TravelSalesAPI api;

    @BeforeEach
    public void setup() throws Exception {
        api = new TravelSalesAPI(new URL("http://junk:8080"));
    }

    @Test
    public void testRandomCities() {

        List<City> cities = api.getCities();
        System.out.println(cities);
    }

    @Test
    public void testPermutations() {
        List<City> cityList = new ArrayList<>();
        cityList.add(new City(0, 0));
        cityList.add(new City(2, 1));
        cityList.add(new City(2, 2));
        cityList.add(new City(0, 1));

        List<List<City>> cities = api.getPermutations(cityList);
        System.out.println(cities);
    }

    @Test
    public void testDistances() {
        List<City> cityList = new ArrayList<>();
        cityList.add(new City(0, 0));
        cityList.add(new City(2, 1));
        cityList.add(new City(2, 2));
        cityList.add(new City(0, 1));
        cityList.add(new City(150, 4));
        cityList.add(new City(20, 15));

        List<List<Tour>> tours = api.getDistances(cityList);
        System.out.println(tours);
    }

    @Test
    public void testShortest() {
        List<City> cityList = new ArrayList<>();
        cityList.add(new City(0, 0));
        cityList.add(new City(2, 1));
        cityList.add(new City(2, 2));
        cityList.add(new City(0, 1));

        List<Tour> tours = api.getShortest(cityList);
        System.out.println(tours);
    }
}
