package com.sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.redis.RedisClient;

public class RedisVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        RedisClient redisClient = RedisClient.create(vertx);
        HttpServer server = Vertx.vertx().createHttpServer();
        JsonObject mongoconfig = new JsonObject()
                .put("connection_string", "mongodb://localhost:27017")
                .put("db_name", "test");

        MongoClient mongoClient = MongoClient.createShared(vertx, mongoconfig);
        redisClient.subscribe("channel", res -> {
            if (res.succeeded()) {
                System.out.println("Subscribed to redis channel");
            } else {
                System.out.println("Channel not connected..");
            }
        });

        server.websocketHandler(new Handler<ServerWebSocket>() {
            @Override
            public void handle(ServerWebSocket serverWebSocket) {
                vertx.eventBus().consumer("io.vertx.redis.channel", (Message<JsonObject> msg) -> {
                    JsonObject value = msg.body().getJsonObject("value");
                    System.out.println("value :: " + value.getString("message"));
                    serverWebSocket.writeBinaryMessage(Buffer.buffer("true"));
                });
            }
        });
        server.listen(8080);
    }
}
