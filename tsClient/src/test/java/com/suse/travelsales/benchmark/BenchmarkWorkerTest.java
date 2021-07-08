package com.suse.travelsales.benchmark;

import com.suse.travelsales.City;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BenchmarkWorkerTest {

    @Test
    public void testSingleThread() throws MalformedURLException {
        BenchmarkConfig config = new BenchmarkConfig();
        config.setBaseURL(new URL("http://localhost:8080"));
        config.setNumThreads(1);
        config.setIterations(2);
        config.setCities(createCities(6));
        config.setPermutations(true);
        config.setDistances(true);
        config.setShortest(true);

        BenchmarkWorker worker = new BenchmarkWorker(config, 1);
        worker.run();

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
