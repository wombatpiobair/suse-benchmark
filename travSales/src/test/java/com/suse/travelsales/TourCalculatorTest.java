package com.suse.travelsales;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    public void badListTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TourCalculator(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new TourCalculator(new ArrayList<City>());
        });
    }

    @Test
    public void permTester() {
        Permuter<City> perm = new Permuter<City>();

        List<List<City>> permutations = perm.permute(cityList);
        for(List<City> pm:permutations) {
            System.out.println(pm);
        }

        long expectedSize = CombinatoricsUtils.factorial(cityList.size());
        assertEquals(expectedSize, (long)permutations.size());
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

        assertEquals(tours.size(), 4);
    }

    @Test
    public void testShortest() {
        TourCalculator calc = new TourCalculator(cityList);
        List<Tour> tours = calc.getShortest();

        BigDecimal distance = new BigDecimal(tours.get(0).getDistance());

        System.out.println("shortest distance: " + distance);
        for(Tour t:tours) {
                System.out.println(" -> " + t.getCities());
        }

        BigDecimal expected = new BigDecimal(6.47213);
        MathContext ctx = new MathContext(4);
        assertEquals(distance.round(ctx), expected.round(ctx));
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
        // should always be at least 2
        assertTrue(tours.size() > 1);
    }
}
