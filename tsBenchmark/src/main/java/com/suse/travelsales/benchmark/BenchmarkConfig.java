package com.suse.travelsales.benchmark;

import com.suse.travelsales.City;

import java.net.URL;
import java.util.List;

public class BenchmarkConfig {
    private URL baseURL;
    private List<City> cities;
    private boolean permutations = false;
    private boolean distances = false;
    private boolean shortest = false;
    private int numThreads = 6;
    private int iterations = 50;

    public int getNumThreads() {
        return numThreads;
    }

    public void setNumThreads(int numThreads) {
        this.numThreads = numThreads;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public URL getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(URL baseURL) {
        this.baseURL = baseURL;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public boolean isPermutations() {
        return permutations;
    }

    public void setPermutations(boolean permutations) {
        this.permutations = permutations;
    }

    public boolean isDistances() {
        return distances;
    }

    public void setDistances(boolean distances) {
        this.distances = distances;
    }

    public boolean isShortest() {
        return shortest;
    }

    public void setShortest(boolean shortest) {
        this.shortest = shortest;
    }
}
