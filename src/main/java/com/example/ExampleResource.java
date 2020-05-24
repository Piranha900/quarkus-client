package com.example;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("api")
public class ExampleResource {

    @Inject
    @RestClient
    ClientResource clientResource;

    @GET
    @Path("/neutrinoflow")
    public String neutrinoFlow() {
        return clientResource.neutrinoFlow();
    }
    @GET
    @Path("/randomneutrino")
    public String randomNeutrino() {
        return clientResource.getRandom();
    }
    @GET
    @Path("/hi")
    public String hi() {
        return clientResource.getHi();
    }
}
