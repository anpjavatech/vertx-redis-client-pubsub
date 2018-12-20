package com.sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

import java.nio.Buffer;

public class ClientVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        JsonObject mongoconfig = new JsonObject()
                .put("connection_string", "mongodb://localhost:27017")
                .put("db_name", "test");

        MongoClient mongoClient = MongoClient.createShared(vertx, mongoconfig);
        HttpClient client = vertx.createHttpClient();
        client.websocket(8080, "localhost", "/", websocket -> {
            websocket.handler(data -> {
                if(data.toString().equalsIgnoreCase("true")){
                    mongoClient.find("products", new JsonObject(), hsn -> {
                        System.out.println("data retrieved :: "+hsn.result());
                    });
                }
            });
        });
    }
}
