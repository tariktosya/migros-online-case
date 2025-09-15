package model;

import java.time.Instant;

public record CourierIntoStore(String courierId,
                               String storeName,
                               Instant time) {
}
