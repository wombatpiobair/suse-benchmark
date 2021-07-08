package com.suse.travelsales.benchmark.api;

import com.suse.travelsales.City;
import com.suse.travelsales.Tour;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class TravelSalesAPI {
    private URL base;
    HttpHeaders headers = new HttpHeaders();


    public TravelSalesAPI(URL baseURL) {
        base = baseURL;
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public  List<City> getCities() {
        RestTemplate restTemplate = new RestTemplate();
        String path = concatenate("ts/random");
        City[] cities = restTemplate.getForObject(path, City[].class);
        return Arrays.asList(cities);
    }

    public  List<List<City>> getPermutations(List<City> cities) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(cities, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<List<City>>> response = restTemplate.exchange(concatenate("ts/permutations"),
                HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<List<List<City>>>() {});

        return response.getBody();
    }

    public  List<List<Tour>> getDistances(List<City> cities) {

        HttpEntity<Object> requestEntity = new HttpEntity<Object>(cities, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<List<Tour>>> response = restTemplate.exchange(concatenate("ts/distances"),
                HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<List<List<Tour>>>() {});

        return response.getBody();
    }

    public  List<Tour> getShortest(List<City> cities) {

        HttpEntity<Object> requestEntity = new HttpEntity<Object>(cities, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Tour>> response = restTemplate.exchange(concatenate("ts/tour"),
                HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<List<Tour>>() {});

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
