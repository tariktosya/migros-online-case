package service;

import model.CourierEntries;
import model.CourierLocation;
import model.Store;
import model.StoreEntry;
import service.interfaces.IStoreService;
import strategy.DistanceStrategy;
import strategy.TimeStrategy;
import strategy.interfaces.IEntryCheckStrategy;
import utils.FileReadUtil;
import utils.MyLogger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreService implements IStoreService {

    private static final String JSON_FILE_NAME = "resources/stores.json";
    private final List<CourierEntries> courierStoreEntries = new ArrayList<>();

    private final List<IEntryCheckStrategy> strategies = List.of(
            new DistanceStrategy(),
            new TimeStrategy()
    );

    public StoreService() {}

    @Override
    public void checkStores(CourierLocation loc) throws IOException {
        List<Store> stores = FileReadUtil.parseStores(new File(JSON_FILE_NAME));

        for (Store store : stores) {

            List<StoreEntry> entries = getEntriesByCourierId(loc);

            boolean allSuccess = isAllSuccessFromStrategyPattern(loc, store, entries);

            if (allSuccess) {
                StoreEntry entry = new StoreEntry(store.name(), loc.timestamp());
                entries.add(entry);
                MyLogger.info("Courier " + loc.courierId() + " entered store " + store.name());
            }
        }
    }

    private boolean isAllSuccessFromStrategyPattern(CourierLocation loc, Store store, List<StoreEntry> entries) {
        return strategies.stream()
                .allMatch(strategy -> strategy.check(loc, store, entries));
    }

    private List<StoreEntry> getEntriesByCourierId(CourierLocation loc) {
        CourierEntries courierEntries = courierStoreEntries.stream()
                .filter(c -> c.courierId().equals(loc.courierId()))
                .findFirst()
                .orElseGet(() -> {
                    CourierEntries newCourier = new CourierEntries(loc.courierId(), new ArrayList<>());
                    courierStoreEntries.add(newCourier);
                    return newCourier;
                });

        return courierEntries.entries();
    }
}
