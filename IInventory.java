import java.util.ArrayList;

public interface IInventory {

    void load(ILoadable loadable);

    void unload(boolean firstInFirstOut);

    void raiseRamp();

    void lowerRamp();

    ArrayList<ILoadable> getCarriedTransportables();

    public boolean isReadyToBeLoaded();

    int getCarsMaxAmount();

    boolean getIsRaised();
}
