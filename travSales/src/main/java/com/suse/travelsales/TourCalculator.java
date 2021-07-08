package com.suse.travelsales;

import java.util.*;

public class TourCalculator {
    // Holds our tour of cities
    private List<City> cities = new ArrayList<City>();

    public TourCalculator(List<City> tour){
        if((null == tour) || tour.size() == 0) {
            throw new IllegalArgumentException("a non-zero length list of cities required");
        }
        this.cities = tour;
    }

    public List<Tour> getShortest() {
        SortedMap<Float, List<Tour>> sortedMap = getDistances();
        List<Tour> tours = sortedMap.get(sortedMap.firstKey());
        return tours;
    }

    public List<List<City>> getPerumtations() {
        Permuter perm = new Permuter<City>();
        return perm.permute(cities);
    }

    public List<List<Tour>> getSortedTours() {
        return new ArrayList(getDistances().values());
    }

    protected SortedMap<Float, List<Tour>> getDistances() {
        List<List<City>> permutations = getPerumtations();
        TreeMap<Float, List<Tour>> distances = new TreeMap<Float, List<Tour>>();
        for (List<City> cities:permutations) {
            float distance = calcDistance(cities);
            Tour tour = new Tour();
            tour.setDistance(distance);
            tour.setCities(cities);

            // add tour to list if distance exists
            if (distances.containsKey(distance)) {
                distances.get(distance).add(tour);
            } else {
                ArrayList<Tour> tours = new ArrayList<>();
                tours.add(tour);
                distances.put(distance, tours);
            }

        }
        return distances;
    }



    protected float calcDistance(List<City> cities) {

        float distanceTotal = 0.0F;

        // iterate through the list except for last city
        for(int i = 0; i < (cities.size() - 1); i++) {
            distanceTotal += cities.get(i).distanceTo(cities.get(i+1));
        }

        // close the loop to the first
        distanceTotal += cities.get(cities.size() -1).distanceTo(cities.get(0));

        return distanceTotal;

    }


}
