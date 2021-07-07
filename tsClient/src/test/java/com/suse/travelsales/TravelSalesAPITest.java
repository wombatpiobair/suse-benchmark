package com.suse.travelsales;

import com.suse.travelsales.client.TravelSalesAPI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TravelSalesAPITest {

    @Test
    public void testRandomCities() {

        List<City> cities = TravelSalesAPI.getCities();
        System.out.println(cities);
    }

    @Test
    public void testPermutations() {
        List<City> cityList = new ArrayList<>();
        cityList.add(new City(0, 0));
        cityList.add(new City(2, 1));
        cityList.add(new City(2, 2));
        cityList.add(new City(0, 1));

        List<List<City>> cities = TravelSalesAPI.getPermutations(cityList);
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

        List<List<Tour>> tours = TravelSalesAPI.getDistances(cityList);
        System.out.println(tours);
    }

    @Test
    public void testShortest() {
        List<City> cityList = new ArrayList<>();
        cityList.add(new City(0, 0));
        cityList.add(new City(2, 1));
        cityList.add(new City(2, 2));
        cityList.add(new City(0, 1));

        List<Tour> tours = TravelSalesAPI.getShortest(cityList);
        System.out.println(tours);
    }
}
