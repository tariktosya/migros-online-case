package strategy;

import model.CourierLocation;
import model.Store;
import model.StoreEntry;
import strategy.interfaces.IEntryCheckStrategy;

import java.time.Duration;
import java.util.List;

public class TimeInSameStoreStrategy implements IEntryCheckStrategy {

    /**
     * return true if reentries to the same store's circumference over 1 minute
     */
    @Override
    public boolean check(CourierLocation loc, Store store, List<StoreEntry> entries) {
        return entries.stream()
                .filter(e -> e.storeName().equals(store.name()))
                .map(StoreEntry::timestamp)
                .max(java.util.Comparator.naturalOrder())
                .map(last -> Duration.between(last, loc.timestamp()).toMinutes() >= 1)
                .orElse(true);
    }
}