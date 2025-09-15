package service.interfaces;

import model.CourierLocation;

import java.io.IOException;

public interface IStoreService {

    void checkStores(CourierLocation location) throws IOException;

}
