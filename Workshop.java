import java.util.ArrayList;

public class Workshop<C extends ILoadable> implements IHasInventory {

    private CarInventory carInventory;
    private InventoryHelper inventoryHelper;
    private int vehicleMaxAmount;
    //private ArrayList<Vehicle> carriedVehicles;
    private double xCord;
    private double yCord;
    private int direction;

    /**
     * Sets the parameters of the Workshop
     * @param vehicleMaxAmount max capacity of cars
     * @param xCord The x coordinate of the workshop
     * @param yCord The y coordinate of the workshop
     * @param direction
     */
    public Workshop(int vehicleMaxAmount, double xCord, double yCord, int direction){
        this.carInventory = new CarInventory( this,vehicleMaxAmount);
        carInventory.lowerRamp();
        this.inventoryHelper = new InventoryHelper(carInventory, this);
        this.vehicleMaxAmount= inventoryHelper.setVehicleMaxAmount(vehicleMaxAmount);
        this.xCord=xCord;
        this.yCord=yCord;
        this.direction=direction;
    }

    /**
     * Load a car into the workshop
     * @param vehicle the car to be loaded
     */
    public void load(C loadable) {
        System.out.println(loadable.getClass());
        if (carInventory.getCarriedTransportables().size() < vehicleMaxAmount) {
            carInventory.load((ILoadable)loadable);
        } else {
            System.out.println("Får inte plats.");
        }
    }

    /**
     * Check wheter or not a car is allowed
     * @param whitelistedCarModels cars allowed
     * @param vehicleToBeChecked the car to be checked
     * @return wheter or not the car is allowed or not
     */
    private boolean checkIfInWhitelist(ArrayList<ILoadable> whitelistedCarModels, ILoadable vehicleToBeChecked){
        for(ILoadable whitelistedCarModel : whitelistedCarModels){
            if(vehicleToBeChecked.getClass() == whitelistedCarModel.getClass()){
                return true;
            }
        }
        return false;
    }

    public boolean isReadyToBeLoaded(){
        return true;
    }

    /**
     * Release a car from the workshop
     */
    public void unloadVehicle(int indexOfVehicleToBeUnloaded) {
        carInventory.unload(indexOfVehicleToBeUnloaded);
    }

    /**
     * @return the x coordinate of the workshop
     */
    public double getXcord(){
        return xCord;
    }

    /**
     * @return the y coordinate of the workshop
     */
    public double getYcord(){
        return yCord;
    }

    /**
     * @return the direction of the workshop
     */
    public int getDirection(){
        return direction;
    }
//Ugh fy fan, hatar att ha den här här
    public double getCurrentSpeed(){
        return 0;
    }

    public boolean getBedAccessible(double currentSpeed, boolean isBedRaised) {
        return true;
    }

    public int getCarsMaxAmount(){
        return vehicleMaxAmount;
    }

    /**
     * @return list of all stored cars
     */
    public ArrayList<ILoadable> getCarriedCars(){
        return carInventory.getCarriedTransportables();
    }

    /**
     * Add a vehicle to the workshop
     * @param vehicle the vehicle to be added
     */
}
