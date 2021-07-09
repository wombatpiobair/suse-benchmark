package com.suse.travelsales.benchmark.api;

import com.suse.travelsales.City;
import com.suse.travelsales.Tour;
import com.suse.travelsales.benchmark.BenchmarkApplication;
import org.apache.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.*;

import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class TravelSalesAPI {
    private URL base;
    HttpHeaders headers = new HttpHeaders();
    static Logger logger = Logger.getLogger(BenchmarkApplication.class.getName());

    public TravelSalesAPI(URL baseURL) {
        base = baseURL;
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public  List<City> getCities() {
        RestTemplate restTemplate = new RestTemplate();
        String path = concatenate("ts/random");
        City[] cities;
        try {
            cities = restTemplate.getForObject(path, City[].class);
        }  catch (ResourceAccessException ex) {
            logger.error("cities failed - no access to host: " + ex.getMessage());
            throw ex;
        } catch (HttpClientErrorException e) {
            logger.error("cities failed with status code: " + e.getStatusCode() + " msg: "+ e.getMessage());
            throw e;
        } catch (HttpServerErrorException re) {
            logger.error("cities failed Server errror: " + re.getStatusCode());
            throw re;
        } catch (RuntimeException rex) {
            logger.error("cities failed errror: " + rex.getClass() + rex.getMessage());
            throw rex;
        }
        return Arrays.asList(cities);
    }

    public  List<List<City>> getPermutations(List<City> cities) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(cities, headers);
        ResponseEntity<List<List<City>>> response;
        try {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.exchange(concatenate("ts/permutations"),
                    HttpMethod.POST, requestEntity,
                    new ParameterizedTypeReference<List<List<City>>>() {
                    });
        } catch (ResourceAccessException ex) {
            logger.error("permutations failed - no access to host: " + ex.getMessage());
            throw ex;
        } catch (HttpClientErrorException e) {
            logger.error("permutations failed with status code: " + e.getStatusCode() + " msg: "+ e.getMessage());
            throw e;
        } catch (HttpServerErrorException re) {
            logger.error("permutations failed Server errror: " + re.getStatusCode());
            throw re;
        } catch (RuntimeException rex) {
            logger.error("permutations failed errror: " + rex.getClass() + rex.getMessage());
            throw rex;
        }



        return response.getBody();
    }

    public  List<List<Tour>> getDistances(List<City> cities) {

        HttpEntity<Object> requestEntity = new HttpEntity<Object>(cities, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<List<Tour>>> response;
        try {
            response = restTemplate.exchange(concatenate("ts/distances"),
                    HttpMethod.POST, requestEntity,
                    new ParameterizedTypeReference<List<List<Tour>>>() {
                    });

        } catch (ResourceAccessException ex) {
            logger.error("distances failed - no access to host: " + ex.getMessage());
            throw ex;
        } catch (HttpClientErrorException e) {
            logger.error("distances failed with status code: " + e.getStatusCode() + " msg: "+ e.getMessage());
            throw e;
        } catch (HttpServerErrorException re) {
            logger.error("distances failed Server errror: " + re.getStatusCode());
            throw re;
        } catch (RuntimeException rex) {
            logger.error("distances failed errror: " + rex.getClass() + rex.getMessage());
            throw rex;
        }
        return response.getBody();
    }

    public  List<Tour> getShortest(List<City> cities) {

        HttpEntity<Object> requestEntity = new HttpEntity<Object>(cities, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Tour>> response;
        try {
            response = restTemplate.exchange(concatenate("ts/tour"),
        HttpMethod.POST, requestEntity,
        new ParameterizedTypeReference<List<Tour>>() {});
        } catch (ResourceAccessException ex) {
            logger.error("shortest failed - no access to host: " + ex.getMessage());
            throw ex;
        } catch (HttpClientErrorException e) {
            logger.error("shortest failed with status code: " + e.getStatusCode() + " msg: "+ e.getMessage());
            throw e;
        } catch (HttpServerErrorException re) {
            logger.error("shortest failed Server errror: " + re.getStatusCode());
            throw re;
        } catch (RuntimeException rex) {
            logger.error("shortest failed errror: " + rex.getClass() + rex.getMessage());
            throw rex;
        }
        return response.getBody();
    }

    public String concatenate(String extraPath)  {
        URL result = base;
        try {
            URI uri = base.toURI();
            String newPath = uri.getPath() + '/' + extraPath;
            URI newUri = uri.resolve(newPath);
            newUri.normalize();
            result =  newUri.toURL();
        } catch (Exception e) {
            throw new RuntimeException("Error concatonating URL: " + e.getMessage());
        }

        return result.toString();
    }


}
