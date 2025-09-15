package service;

import model.CourierLocation;
import utils.DistanceUtil;
import utils.FileUtil;

import java.util.*;
import java.util.stream.IntStream;

public class DistanceTracker {

    private static final String FILE_NAME = "courier_locations.txt";
    private static final DistanceTracker instance = new DistanceTracker();
    private final Map<String, List<CourierLocation>> courierLocations = new HashMap<>();

    private DistanceTracker() {
    }

    public static DistanceTracker getInstance() {
        return instance;
    }

    public void addLocation(CourierLocation loc) {
        courierLocations
                .computeIfAbsent(loc.courierId(), k -> new ArrayList<>())
                .add(loc);
        FileUtil.writeTxtFile(loc, FILE_NAME);
    }

    public double getTotalTravelDistance(String courierId) {
        List<CourierLocation> locs = courierLocations.get(courierId);
        if (Objects.isNull(locs) || locs.size() < 2) return 0;

        return calculateTotalDistance(locs);
    }

    private static double calculateTotalDistance(List<CourierLocation> locs) {
        return IntStream.range(1, locs.size())
                .mapToDouble(i -> DistanceUtil.calculateDistance(
                        locs.get(i - 1).lat(), locs.get(i - 1).lng(),
                        locs.get(i).lat(), locs.get(i).lng()))
                .sum();
    }
}
