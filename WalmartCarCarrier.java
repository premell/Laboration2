import java.awt.*;
import java.util.ArrayList;

public class WalmartCarCarrier extends Car implements IHasInventory{

    private int carsMaxAmount;
    private boolean firstInFirstOut= false;

    /**
     * Creates the carrier bed of the truck
     */
    private CarInventory carInventory;


    /**
     * Sets parameters of the truck
     */
    public WalmartCarCarrier(){
        super(
                false,
                 true,
                 Color.white,
                600,
                2,
                "Truck"
        );
        this.carsMaxAmount=6;
        this.carInventory = new CarInventory(this, carsMaxAmount);
    }

    /**
     * Returns the speedfactor of the truck
     * @return speedfactor the acceleration of the truck
     */
    public double getSpeedFactor(){
        return (getEnginePower()*0.01);
    }

    /**
     * Checks if the truck is moving
     */
    public boolean isReadyToBeLoaded(){
        if(getCurrentSpeed()>0.01){
            return false;
        }
        return true;
    }

    /**
     * Raise the ramp of the truck
     */
    public void raiseRamp(){
        carInventory.raiseRamp();
    }
    /**
     * Lower the ramp of the truck
     */
    public void lowerRamp(){
        carInventory.lowerRamp();
    }

    /**
     * Load a car unto the truck
     * @param ILoadable car to be loaded
     */
    public void load(ILoadable ILoadable){
        if(ILoadable.getCarSized() && ILoadable.getTruckSized()) {
            carInventory.load(ILoadable);
        }else{
            System.out.println("Must be car sized.");
        }
    }

    /**
     * Unload a car from the truck
     */
    public void unloadCar(){
        carInventory.unload(firstInFirstOut);
    }

    /**
     * @return all the cars currenly being transported
     */
    public ArrayList<ILoadable> getCarriedCars(){
        return carInventory.getCarriedTransportables();
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
    @Override
    public void move() {
        super.move();

        for(ILoadable loadable : carInventory.getCarriedTransportables()) {
            loadable.setPositionDuringTransport(getXcord(),getYcord());
        }
    }
}
