package com.suse.travelsales.benchmark;

import com.suse.travelsales.City;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BenchmarkPoolTest {

    @Test
    public void testSingleThread() throws MalformedURLException {
        BenchmarkConfig config = new BenchmarkConfig();
        config.setBaseURL(new URL("http://localhost:8080"));
        config.setNumThreads(1);
        config.setIterations(2);
        config.setCities(createCities(4));
        config.setPermutations(true);
        config.setDistances(true);
        config.setShortest(true);

        BenchmarkPool pool = new BenchmarkPool(config);
        pool.execute();

        try {
            boolean status = pool.executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMultiThread() throws MalformedURLException {
        BenchmarkConfig config = new BenchmarkConfig();
        config.setBaseURL(new URL("http://localhost:8080"));
        config.setNumThreads(4);
        config.setIterations(5);
        config.setCities(createCities(9));
        config.setPermutations(true);
        config.setDistances(true);
        config.setShortest(true);

        BenchmarkPool pool = new BenchmarkPool(config);
        pool.execute();

        try {
            boolean status = pool.executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    private List<City> createCities(int count) {
        List<City> cities = new ArrayList<>();
        // change to fixed list
        for (int i=0; i<count; i++) {
            cities.add(new City());
        }
        return cities;
    }
}
