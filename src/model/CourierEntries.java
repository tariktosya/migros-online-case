package model;

import java.util.List;

public record CourierEntries(
        String courierId,
        List<StoreEntry> entries) {
}
