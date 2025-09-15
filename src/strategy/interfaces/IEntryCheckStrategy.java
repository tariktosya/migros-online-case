package strategy.interfaces;

import model.CourierLocation;
import model.Store;
import model.StoreEntry;

import java.util.List;

public interface IEntryCheckStrategy {

    boolean check(CourierLocation loc, Store store, List<StoreEntry> previousEntries);

}
