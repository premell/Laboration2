import java.util.ArrayList;

public class Ferry extends Vehicle implements IHasInventory{

    private CarInventory carInventory;
    private double speedFactor;
    private boolean firstInFirstOut= true;
    private int loadableMaxAmount;

    /**
     * Sets parameters of the truck
     */
    public Ferry(){
        super("Ferry",200,false,false);
        loadableMaxAmount=100;
        this.carInventory=new CarInventory(this, loadableMaxAmount);
        speedFactor=1;
    }
    /**
     * Load a car unto the truck
     * @param ILoadable car to be loaded
     */
    public void load(ILoadable ILoadable){
        if(ILoadable.getTruckSized() || ILoadable.getCarSized() || isReadyToBeLoaded()){
            carInventory.load(ILoadable);
        }
    }

    /**
     * Unload a car from the truck
     */
    public void unload(ILoadable ILoadable){
        carInventory.unload(firstInFirstOut);
    }

    /**
     * Checks if the truck is moving
     */
    public boolean isReadyToBeLoaded(){
        if(getCurrentSpeed()>0.01){
            return false;
        }
        else {
            return true;
        }
    }
    /**
     * Returns the speedfactor of the truck
     * @return speedfactor the acceleration of the truck
     */
    public double getSpeedFactor(){
        return getEnginePower()*0.01;
    }

    /**
     * @return all the cars currenly being transported
     */
    public ArrayList<ILoadable> getCarriedCars(){
        return carInventory.getCarriedTransportables();
    }
    /**
     * Lower the ramp of the truck
     */
    public void lowerRamp(){
        carInventory.lowerRamp();
    }
    /**
     * Raise the ramp of the truck
     */
    public void raiseRamp(){
        carInventory.raiseRamp();
    }

    /**
     * Overides the gas function with the requriment that the ramp is up
     * @param amount Specifies amount of which to increment speed.
     */
    @Override
    public void gas(double amount){
        if(InventoryHelper.gasAvailable(carInventory.getIsRaised())){
            super.gas(amount);
        }
    }

    /**
     * Overides the move to update the position of the cars
     */
    @Override
    public void move() {
        super.move();

        for(ILoadable loadable : carInventory.getCarriedTransportables()) {
            loadable.setPositionDuringTransport(getXcord(),getYcord());
        }
    }
}
