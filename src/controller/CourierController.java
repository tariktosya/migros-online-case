package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import model.CourierLocation;
import service.CourierService;
import service.StoreService;
import service.interfaces.ICourierService;
import service.interfaces.IStoreService;
import utils.HttpHelperUtil;
import utils.JsonUtil;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Objects;

public class CourierController extends BaseController {

    private static final String POST = "POST";
    private static final String GET = "GET";
    private final ICourierService courierService;

    public CourierController() {
        IStoreService storeService = new StoreService();
        this.courierService = new CourierService(storeService);
    }

    @Override
    public void register(HttpServer server) {
        server.createContext("/courier/location", this::handleLocation);
        server.createContext("/courier/distance", this::handleDistance);
    }

    private void handleLocation(HttpExchange exchange) {
        HttpHelperUtil.handleRequest(exchange, POST, () -> {
            try (InputStream is = exchange.getRequestBody()) {

                String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                String courierId = JsonUtil.getJsonStringRequired(json, "courierId");
                Double lat = JsonUtil.getJsonDoubleRequired(json, "lat");
                Double lng = JsonUtil.getJsonDoubleRequired(json, "lng");
                Instant time = Instant.ofEpochSecond(JsonUtil.getJsonLongRequired(json, "timestamp"));

                courierService.receiveLocation(new CourierLocation(
                        courierId, lat, lng, time
                ));
                HttpHelperUtil.sendResponse(exchange, 200, "Location received");
            } catch (Exception e) {
                HttpHelperUtil.sendResponse(exchange, 500, "Error: " + e.getMessage());
            }
        });
    }

    private void handleDistance(HttpExchange exchange) {
        HttpHelperUtil.handleRequest(exchange, GET, () -> {
            String query = exchange.getRequestURI().getQuery();
            if (Objects.isNull(query) || !query.contains("=")) {
                HttpHelperUtil.sendResponse(exchange, 400, "Missing courierId");
                return;
            }

            String courierId = query.split("=")[1];
            double distance = courierService.getTotalDistance(courierId);
            HttpHelperUtil.sendResponse(exchange, 200, "Courier : " + courierId + " Total Distance " + distance + " mt");
        });
    }
}
