package service.interfaces;

import model.CourierLocation;

public interface ICourierService {

    void receiveLocation(CourierLocation courierLocation) throws Exception;
    double getTotalDistance(String courierId);
}
