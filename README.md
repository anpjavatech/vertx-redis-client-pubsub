# vertx-redis-client-pubsub
Vertx Project to integrate with Redis for Pub Sub Model
This project is to deal with redis and Websocket integration on vertx.

# Things to remember:

- Vertx
- Redis (Should install in machine)
- Websocket (No UI implementation. Just written a service to consume the data which is pushed in websocket.)

# Project details:

Consist of 3 services
- Service which inserts periodically to Mongo and Push data to redis channel.
- Service which listens to redis Channel and Write data to websocket.
- Service which listens to websocket. This in futuer may be converted it as UI.

# Things to achieve

Live Update of client(UI) when insert happens in Mongo. This can be modified to tack the live updates which happens in backend. For eg: live cricket score :).
