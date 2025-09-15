package strategy;

import model.CourierLocation;
import model.Store;
import model.StoreEntry;
import strategy.interfaces.IEntryCheckStrategy;
import utils.DistanceUtil;

import java.util.List;

public class DistanceStrategy implements IEntryCheckStrategy {

    private static final double MIN_DISTANCE = 100;// 100 mt

    /**
     * return true if courier enters radius of 100 meters
     */
    @Override
    public boolean check(CourierLocation loc, Store store, List<StoreEntry> previousEntries) {
        double distance = DistanceUtil.calculateDistance(loc.lat(), loc.lng(), store.lat(), store.lng());
        return distance <= MIN_DISTANCE;
    }
}