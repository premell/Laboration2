import java.util.ArrayList;

public interface IHasInventory {

    double getXcord();

    double getYcord();

    double getCurrentSpeed();

    double getDirection();

    public boolean isReadyToBeLoaded();

    ArrayList<ILoadable> getCarriedCars();

}
