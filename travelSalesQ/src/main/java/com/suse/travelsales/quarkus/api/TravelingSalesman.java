package com.suse.travelsales.quarkus.api;

import com.suse.travelsales.City;
import com.suse.travelsales.Tour;
import com.suse.travelsales.TourCalculator;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ts")
public class TravelingSalesman {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/permutations")
    public List<List<City>> getPerumutations(List<City> cities) {
        TourCalculator calc = new TourCalculator(cities);
        return calc.getPerumtations();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/distances")
    public List<List<Tour>> getDistances(List<City> cities) {
        TourCalculator calc = new TourCalculator(cities);
        return calc.getSortedTours();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tour")
    public List<Tour> getShortest(List<City> cities) {
        TourCalculator calc = new TourCalculator(cities);
        return calc.getShortest();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/random")
    public List<City> getRandomList() {
        int number = 5;
        List<City> cities = new ArrayList<City>();
        for(int i=0; i<number; i++) {
            cities.add(new City());
        }
        return cities;
    }
}