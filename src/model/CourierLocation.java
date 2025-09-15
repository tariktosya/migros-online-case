package model;

import java.time.Instant;

public record CourierLocation(
        String courierId,
        double lat,
        double lng,
        Instant timestamp) {
}

