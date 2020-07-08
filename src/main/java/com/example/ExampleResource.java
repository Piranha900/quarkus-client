package com.example;

import io.quarkus.scheduler.Scheduled;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("api")
public class ExampleResource {

    private final Logger log = LoggerFactory.getLogger(ExampleResource.class);
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
    @Scheduled(every="10s")
    public void hi() {
        log.info("access to /hi");
        /*return */clientResource.getHi();
    }
}
