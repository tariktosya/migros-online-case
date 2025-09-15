package service;

import model.CourierLocation;
import service.interfaces.ICourierService;
import service.interfaces.IStoreService;
import utils.*;

public class CourierService implements ICourierService {

    private final IStoreService storeService;

    public CourierService(IStoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public void receiveLocation(CourierLocation courierLocation) throws Exception {
        DistanceTracker.getInstance().addLocation(courierLocation);
        MyLogger.info("Location received: " + courierLocation);
        storeService.checkStores(courierLocation);
    }

    @Override
    public double getTotalDistance(String courierId) {
        double totalDistance = DistanceTracker.getInstance().getTotalTravelDistance(courierId);

        MyLogger.info("Courier: " + courierId + "  Total distance: " + totalDistance);
        return totalDistance;
    }
}
