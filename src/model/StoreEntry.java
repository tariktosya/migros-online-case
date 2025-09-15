package model;

import java.time.Instant;

public record StoreEntry(
        String storeName,
        Instant timestamp) {
}