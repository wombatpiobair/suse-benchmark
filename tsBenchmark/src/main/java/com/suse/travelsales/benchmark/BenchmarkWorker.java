package com.suse.travelsales.benchmark;

import com.suse.travelsales.City;
import com.suse.travelsales.Tour;
import com.suse.travelsales.benchmark.api.TravelSalesAPI;
import org.apache.log4j.Logger;


import java.net.URL;
import java.util.List;

public class BenchmarkWorker implements Runnable {
    TravelSalesAPI api;
    BenchmarkConfig config;
    int id = 0;
    Logger logger = Logger.getLogger(getClass().getName());

    public BenchmarkWorker(BenchmarkConfig config, int workerNumber) {
        this.config = config;
        this.id = workerNumber;
        try {
            api = new TravelSalesAPI(config.getBaseURL());
        } catch (Exception e) {
            throw new RuntimeException("error creating url: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            logger.info("Begin worker: " + id);
            for (int i = 0; i < config.getIterations(); i++) {
                if (config.isPermutations()) {
                    logger.info("permutations");
                    List<List<City>> perms = api.getPermutations(config.getCities());
                    logger.info("Worker: " + id + "  calculated " + perms.size() + " perumtations");
                }
                if (config.isDistances()) {
                    List<List<Tour>> tours = api.getDistances(config.getCities());
                    logger.info("Worker: " + id + "  calculated distances for " + tours.size() + " tour collections");
                }

                if (config.isShortest()) {
                    List<Tour> tours = api.getShortest(config.getCities());
                    logger.info("Worker: " + id + "  calculated shortest distance with " + tours.size() + " unique solutions");
                }
            }
        } catch (Exception e) {
            logger.error("worker thread " + id + " interrupted: ");
        }

    }
}
