package com.suse.travelsales.benchmark;

import com.suse.travelsales.City;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class BenchmarkPool {

    int iterations = 50;
    ThreadPoolExecutor executor;
    BenchmarkConfig config;
    List<BenchmarkWorker> workers;
    Logger logger = Logger.getLogger(getClass().getName());

    public BenchmarkPool(BenchmarkConfig config) {
        this.config = config;
        initWorkers();
        executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(config.getNumThreads());
        executor.setKeepAliveTime(1, TimeUnit.MINUTES);

    }

    private void initWorkers() {
        workers = new ArrayList<>();
        for(int i=0; i<config.getNumThreads(); i++) {
            workers.add(new BenchmarkWorker(config, i));
        }

    }

    public void execute() {
        logger.info("Start workers");
        for(BenchmarkWorker worker:workers) {
            logger.info("Executing worker #" + worker.id);
            executor.submit(worker);
        }
        executor.shutdown();
    }
}
