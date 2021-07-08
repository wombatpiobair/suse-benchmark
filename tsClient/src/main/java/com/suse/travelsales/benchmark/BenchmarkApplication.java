package com.suse.travelsales.benchmark;

import com.suse.travelsales.City;
import org.apache.commons.cli.*;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


public class BenchmarkApplication {

    static Logger logger = Logger.getLogger(BenchmarkApplication.class.getName());
    BenchmarkConfig config = new BenchmarkConfig();

    public static void main(String[] args) {

        logger.info("STARTING THE APPLICATION: args = " + args.length);


        new BenchmarkApplication().run(args);
        logger.info("APPLICATION FINISHED");
    }

    private void run(String... args) {
        Options options = createOptions();
        CommandLine cmd = parseCommandLine(options, args);

        int numCities = 6;
        if (cmd.hasOption(CITIES)) {
            numCities = Integer.getInteger(cmd.getOptionValue(CITIES));
        }
        List<City> cities = createCities(numCities);
        config.setCities(cities);

        int numThread = 6;
        if (cmd.hasOption(THREADS)) {
            numThread = Integer.getInteger(cmd.getOptionValue(THREADS));
            config.setNumThreads(numThread);
        }

        if (cmd.hasOption(ITERATIONS)) {
            int iter = Integer.getInteger(cmd.getOptionValue(ITERATIONS));
            config.setIterations(iter);
        }

        List<String> argList = cmd.getArgList();
        String baseURL = "http://localhost:8080";
        if (argList.size() > 1) {
            baseURL = argList.get(1);
            try {
                config.setBaseURL(new URL(baseURL));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e.getMessage());
            }
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


        BenchmarkPool pool = new BenchmarkPool(config);
        pool.execute();
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


        return options;
    }

    private void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        String header = "\nRun benchmarks against traveling salesmen instances\n\nbaseurl = location of services\n\n";
        formatter.printHelp("smokeTest <options> [threads] [baseurl]", header, options, "", false);

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
