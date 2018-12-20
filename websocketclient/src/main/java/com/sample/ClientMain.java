package com.sample;

import io.vertx.core.Vertx;

public class ClientMain {
    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(ClientVerticle.class.getName());
    }
}
