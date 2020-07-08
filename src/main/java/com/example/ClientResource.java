package com.example;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/*@RegisterRestClient(baseUri = "http://quarkus-app:9900")*/
@RegisterRestClient(baseUri = "http://localhost:9900")
@Path("/apidue")
public interface ClientResource {

    @Inject
    Vertx vertx = null;

    @GET
    @Path("/getneutrinoflow")
    default String neutrinoFlow(){

        WebClient client = WebClient.create(vertx);

        return client.get("/getneutrinoflow").send().onItem().apply(resp -> {
            return resp.statusCode()== HttpStatus.SC_OK?
                    resp.bodyAsJsonObject() :
                    new JsonObject()
                            .put("code", resp.statusCode())
                            .put("message", resp.statusMessage());}).toString();
    };

    @GET
    @Path("/randomneutrino")
    default String getRandom(){

        WebClient client = WebClient.create(vertx);

        return client.get("/randomneutrino").send().onItem().apply(resp -> {
            if (resp.statusCode() == HttpStatus.SC_OK) {
                return resp.bodyAsJsonObject();
            } else {
                return new JsonObject()
                        .put("code", resp.statusCode())
                        .put("message", resp.statusMessage());
            }
        }).toString();
    }

    @GET
    @Path("/hi")
    default void getHi(){

        WebClient client = WebClient.create(vertx);

        client.get("/hi").send().onItem().apply(resp -> {
            return resp.statusCode()== HttpStatus.SC_OK?
                    resp.bodyAsJsonObject() :
                    new JsonObject()
                            .put("code", resp.statusCode())
                            .put("message", resp.statusMessage());}).toString();
    };

}

