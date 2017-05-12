package com.foo;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class CountryApi {
    private final CountryService countryService;

    @Inject
    public CountryApi(CountryService countryService) {
        this.countryService = countryService;
    }

    @GET
    @Path("/{code}")
    public Country get(@PathParam("code") String code) {
        return countryService.get("be");
    }
}
