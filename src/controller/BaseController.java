package controller;

import com.sun.net.httpserver.HttpServer;

public abstract class BaseController {

    public abstract void register(HttpServer server);

    public static void registerAll(HttpServer server) {
        new CourierController().register(server);
    }
}

