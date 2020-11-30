import java.util.ArrayList;

public interface IHasInventory {

    double getXcord();

    double getYcord();

    double getCurrentSpeed();

    int getDirection();

    public boolean isReadyToBeLoaded();

    ArrayList<ILoadable> getCarriedCars();

}
