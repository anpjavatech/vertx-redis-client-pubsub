package com.sample;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class ServerMain {
    public static void main(String[] args) {


        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(RedisVerticle.class.getName());
    }
}
