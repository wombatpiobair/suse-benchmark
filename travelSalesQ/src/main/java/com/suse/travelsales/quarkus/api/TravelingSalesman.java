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

@Path("/")
public class TravelingSalesman {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("permutations")
    public Response getPerumutations(List<City> cities) {
        TourCalculator calc = new TourCalculator(cities);
        return Response.ok(calc.getPerumtations()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("distances")
    public Response getDistances(List<City> cities) {
        TourCalculator calc = new TourCalculator(cities);
        return Response.ok(calc.getSortedTours()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("tour")
    public Response getShortest(List<City> cities) {
        TourCalculator calc = new TourCalculator(cities);
        return Response.ok(calc.getShortest()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("random")
    public Response getRandomList() {
        int number = 5;
        List<City> cities = new ArrayList<City>();
        for(int i=0; i<number; i++) {
            cities.add(new City());
        }
        return Response.ok(cities).build();
    }
}