package com.suse.travelsales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TourCalculatorTest {

    List<City> cityList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        cityList.add(new City(0, 0));
        cityList.add(new City(2, 1));
        cityList.add(new City(2, 2));
        cityList.add(new City(0, 1));
    }

    @Test
    public void permTester() {
        Permuter<City> perm = new Permuter<City>();

        for(List<City> pm:perm.permute(cityList)) {
            System.out.println(pm);
        }
    }

    @Test
    public void testTourMaker() {
        TourCalculator calc = new TourCalculator(cityList);
        List<List<Tour>> tours = calc.getSortedTours();

        for(List<Tour> tlist: tours) {
            System.out.println("cities for distance: " + tlist.get(0).getDistance());
            for(Tour t:tlist)
            System.out.println(" -> " + t.getCities());
        }
    }

    @Test
    public void testShortest() {
        TourCalculator calc = new TourCalculator(cityList);
        List<Tour> tours = calc.getShortest();

        System.out.println("shortest distance: " + tours.get(0).getDistance());
        for(Tour t:tours) {
                System.out.println(" -> " + t.getCities());
        }
    }

    @Test
    public void testShortestRandom() {
        List<City> localList = new ArrayList<>();

        localList.add(new City());
        localList.add(new City());
        localList.add(new City());
        localList.add(new City());
        localList.add(new City());
        localList.add(new City());
        localList.add(new City());
        localList.add(new City());
        localList.add(new City());
        localList.add(new City());


        TourCalculator calc = new TourCalculator(localList);

        /*
        List<List<Tour>> sortedtours = calc.getSortedTours();
        for(List<Tour> tlist: sortedtours) {
            System.out.println("cities for distance: " + tlist.get(0).getDistance());
            for(Tour t:tlist)
                System.out.println(" -> " + t.getCities());
        }

         */

        List<Tour> tours = calc.getShortest();

        System.out.println("random list - shortest distance: " + tours.get(0).getDistance());
        for(Tour t:tours) {
            System.out.println(" -> " + t.getCities());
        }
    }
}
