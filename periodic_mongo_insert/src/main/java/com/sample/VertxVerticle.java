package com.sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.redis.RedisClient;

import java.time.Instant;

public class VertxVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        JsonObject mongoconfig = new JsonObject()
                .put("connection_string", "mongodb://localhost:27017")
                .put("db_name", "test");

        MongoClient mongoClient = MongoClient.createShared(vertx, mongoconfig);
        RedisClient redisClient = RedisClient.create(vertx);

        Long timerId = vertx.setPeriodic(3000, han -> {
            int counter = (int) (Math.random() * 1000);
            JsonObject product1 = new JsonObject().put("itemId", counter).put("name", "Cooler-" + counter).put("price", "100.0" + counter).put("timestamp", System.currentTimeMillis());
            System.out.println("Timer triggered..");
            mongoClient.save("products", product1, id -> {
                redisClient.publish("channel", "Mongo insert successfull", res -> {
                    if (res.succeeded()) {
                        System.out.println("Publish to channel is successded");
                    } else {
                        System.out.println("Publish to channel is failed");
                    }
                });
            });
        });
    }
}
