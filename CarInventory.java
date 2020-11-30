import java.util.ArrayList;


public class CarInventory implements IInventory {

    /**
     * Creates the helper for the CarrierBed functions
     */
    private InventoryHelper inventoryHelper;
    private boolean isRaised;

    private int carsMaxAmount;

    private ArrayList<ILoadable> carriedTransportables= new ArrayList<ILoadable>();

    private IHasInventory bedOwner;

    /**
     * Set parameters for the CarrierBed
     * @param bedOwner the owner of the carrierbed
     * @param carsMaxAmount the max capacity of cars
     */
    public CarInventory(IHasInventory bedOwner, int carsMaxAmount){
        inventoryHelper = new InventoryHelper(this, bedOwner);
        this.bedOwner=bedOwner;
        this.setCarsMaxAmount(carsMaxAmount);
        this.isRaised=true;
    }

    /**
     * Sets max capacity
     */
    private void setCarsMaxAmount(int carsMaxAmount){
        this.carsMaxAmount= inventoryHelper.setVehicleMaxAmount(carsMaxAmount);
    }

    /**
     * @return the max capacity of the bed
     */
    public int getCarsMaxAmount(){
        return this.carsMaxAmount;
    }

    /**
     * @return the current cars on the bed
     */
    public ArrayList<ILoadable> getCarriedTransportables(){
        return carriedTransportables;
    }

    /**
     * @return wheter or not the bed ramp isRaised
     */
    public boolean getIsRaised(){
        return isRaised;
    }

    /**
     * Raise the ramp of the bed
     */
    public void raiseRamp(){
        isRaised= inventoryHelper.raiseRamp();
    }

    /**
     * Lower the ramp of the bed
     */
    public void lowerRamp(){
        isRaised=inventoryHelper.lowerRamp();
    }

    /**
     *
     * @return wheter or not the bed is accessible
     */
    public boolean isReadyToBeLoaded() {
        if(!bedOwner.isReadyToBeLoaded()){
            System.out.println("The bed is currently not accessible");
            return false;
        }
        if(isRaised){
            System.out.println("Bed must be lowered.");
            System.out.println("inte nice.");
            return false;
        }
        return true;
    }

    /**
     * Loads a car onto the carrier bed
     * @param loadable car which is about to get loaded
     */
    public void load(ILoadable loadable){
        if(isReadyToBeLoaded()) {
            carriedTransportables=inventoryHelper.load(loadable);
            loadable.setCurrentlyTransported();
            loadable.setPositionDuringTransport(bedOwner.getXcord(),bedOwner.getYcord());
        }
    }

    /**
     * unload the inventory
     * @param firstInFirstOut if unloading from front or back
     */
    public void unload(boolean firstInFirstOut){
        int indexOfLoadable;
        if(firstInFirstOut){
            indexOfLoadable=0;
        }else{
            indexOfLoadable= carriedTransportables.size()-1;
        }

        carriedTransportables.get(indexOfLoadable).setPositionDuringTransport(bedOwner.getXcord(),bedOwner.getYcord());
        carriedTransportables.get(indexOfLoadable).dropOff();
        carriedTransportables =inventoryHelper.unload(indexOfLoadable);
    }

    public void unload(int indexOfCarToBeUnloaded){


        carriedTransportables.get(indexOfCarToBeUnloaded).setPositionDuringTransport(bedOwner.getXcord(),bedOwner.getYcord());
        carriedTransportables.get(indexOfCarToBeUnloaded).dropOff();
        carriedTransportables =inventoryHelper.unload(indexOfCarToBeUnloaded);
    }
}