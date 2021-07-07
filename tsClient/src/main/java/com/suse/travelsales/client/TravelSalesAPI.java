package com.suse.travelsales.client;

import com.suse.travelsales.City;
import com.suse.travelsales.Tour;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class TravelSalesAPI {

    public static List<City> getCities() {
        RestTemplate restTemplate = new RestTemplate();
        City[] cities = restTemplate.getForObject("http://localhost:8080/ts/random", City[].class);
        return Arrays.asList(cities);
    }

    public static List<List<City>> getPermutations(List<City> cities) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(cities, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<List<City>>> response = restTemplate.exchange("http://localhost:8080/ts/permutations",
                HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<List<List<City>>>() {});

        return response.getBody();
    }

    public static List<List<Tour>> getDistances(List<City> cities) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(cities, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<List<Tour>>> response = restTemplate.exchange("http://localhost:8080/ts/distances",
                HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<List<List<Tour>>>() {});

        return response.getBody();
    }public static List<Tour> getShortest(List<City> cities) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(cities, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Tour>> response = restTemplate.exchange("http://localhost:8080/ts/tour",
                HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<List<Tour>>() {});

        return response.getBody();
    }


}
