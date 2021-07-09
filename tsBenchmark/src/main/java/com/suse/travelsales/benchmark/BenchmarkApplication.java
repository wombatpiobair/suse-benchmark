package com.suse.travelsales.benchmark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suse.travelsales.City;
import org.apache.commons.cli.*;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;


public class BenchmarkApplication {

    static Logger logger = Logger.getLogger(BenchmarkApplication.class.getName());
    BenchmarkConfig config = new BenchmarkConfig();

    public static void main(String[] args) {

        logger.info("STARTING THE APPLICATION: args = " + args.length);


        new BenchmarkApplication().run(args);

        //logger.info("APPLICATION FINISHED");
    }

    private void run(String... args) {
        Options options = createOptions();
        CommandLine cmd = parseCommandLine(options, args);

        int numCities = 6;
        if (cmd.hasOption(CITIES)) {
            String value = cmd.getOptionValue(CITIES);
            logger.info("Setting cities to " + value);
            numCities = Integer.parseInt(value);

        }
        List<City> cities = createCities(numCities);
        config.setCities(cities);

        int numThread = 6;
        if (cmd.hasOption(THREADS)) {
            String value = cmd.getOptionValue(THREADS);
            logger.info("Setting threads to " + value);
            numThread = Integer.parseInt(value);
            config.setNumThreads(numThread);
        }

        if (cmd.hasOption(ITERATIONS)) {
            String value = cmd.getOptionValue(ITERATIONS);
            int iter = Integer.parseInt(value);
            config.setIterations(iter);
        }

        if (cmd.hasOption(PERMUTATIONS)) {
            config.setPermutations(true);
        }
        if (cmd.hasOption(DISTANCES)) {
            config.setDistances(true);
        }
        if (cmd.hasOption(SHORTEST)) {
            config.setShortest(true);
        }

        List<String> argList = cmd.getArgList();
        String baseURL = "http://localhost:8080";
        logger.info("argList size = " + argList.size());
        if (argList.size() > 0) {
            baseURL = argList.get(0);
            logger.info("baseurl = " + baseURL);
        }
        try {
            config.setBaseURL(new URL(baseURL));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        logger.info("Starting benchmark with " + config.getNumThreads() + " threads");
        logger.info("Using base URL " + config.getBaseURL());

        if (cmd.hasOption(EXIT)) {
            ObjectMapper mapper = new ObjectMapper();
            String json = "";
            try {
                json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            logger.info("Exiting with config: " + json);
            System.exit(0);
        }

        BenchmarkPool pool = new BenchmarkPool(config);
        long startmilis = System.currentTimeMillis();
        pool.execute();

        // wait here until the thread pool has terminated
        try {
            boolean status = pool.executor.awaitTermination(20, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endmilis = System.currentTimeMillis();
        printReport(endmilis-startmilis);

    }

    private CommandLine parseCommandLine(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        String prefix = "";

        try {
            cmd = parser.parse(options, args, true);

            if (cmd.hasOption(HELP)) {
                printHelp(options);
                System.exit(0);
            }

        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            logger.error("unable to init SmokeTestRunner", e);
            printHelp(options);
            System.exit(0);
        }

        return cmd;
    }



    public static final String HELP = "h";
    public static final String CITIES = "c";
    public static final String PERMUTATIONS = "p";
    public static final String DISTANCES = "d";
    public static final String SHORTEST = "s";
    public static final String THREADS = "t";
    public static final String ITERATIONS = "i";
    public static final String EXIT = "e";

    private Options createOptions() {
        Options options = new Options();

        Option option = new Option(HELP, "help", false, "print this message");
        options.addOption(option);

        option = Option.builder(CITIES)
                .longOpt("cities")
                .desc("number of cities (default 6)")
                .hasArg()
                .build();
        options.addOption(option);

        option = Option.builder(PERMUTATIONS)
                .longOpt("perms")
                .desc("get list of all perumutations")
                .build();
        options.addOption(option);

        option = Option.builder(DISTANCES)
                .longOpt("dist")
                .desc("get distances")
                .build();
        options.addOption(option);

        option = Option.builder(SHORTEST)
                .longOpt("shortest")
                .desc("get the shortest routes")
                .build();
        options.addOption(option);

        option = Option.builder(THREADS)
                .longOpt("threads")
                .desc("number of parallel threads")
                .hasArg()
                .build();
        options.addOption(option);
        option = Option.builder(ITERATIONS)
                .longOpt("iterations")
                .desc("number of loops through requests per thread")
                .hasArg()
                .build();
        options.addOption(option);
        option = Option.builder(EXIT)
                .longOpt("exit")
                .desc("exit before benchmarks")
                .build();
        options.addOption(option);


        return options;
    }

    private void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        String header = "\nRun benchmarks against traveling salesmen instances\n\nbaseurl = location of services\n\n";
        formatter.printHelp("smokeTest <options> [baseurl]", header, options, "", false);

    }

    private List<City> createCities(int count) {
        return getNormalizedCityList().subList(0,count);
    }

    private void printReport(long elapsed) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n\n========================\n");
        System.out.println("Total elapsed time in milis:" + elapsed);
        System.out.println("\n========================\n");
        System.out.println("With config: " + json);
    }

    private List<City> getNormalizedCityList() {
        List<City> cities = new ArrayList<>();
        cities.add(new City(101,183));
        cities.add(new City( 69,159));
        cities.add(new City(195,67));
        cities.add(new City(171,168));
        cities.add(new City(25,2));
        cities.add(new City(171,149));
        cities.add(new City(29,19));
        cities.add(new City(177,41));
        cities.add(new City(50, 144));
        cities.add(new City(164,84));
        cities.add(new City(154, 149));
        cities.add(new City(133, 86));
        cities.add(new City(129,92));
        cities.add(new City(168, 23));
        cities.add(new City(114,118));
        cities.add(new City(114, 125));
        cities.add(new City(96,92));
        cities.add(new City(9,45));
        cities.add(new City(149,145));
        cities.add(new City(42,120));

        return cities;
    }
}
