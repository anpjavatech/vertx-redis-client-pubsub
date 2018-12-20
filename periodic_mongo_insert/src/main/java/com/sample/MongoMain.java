package com.sample;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class MongoMain {
    public static void main(String[] args) {

        DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("http.port", 8081));
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(VertxVerticle.class.getName(), options);
    }
}
