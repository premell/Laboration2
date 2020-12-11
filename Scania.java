import java.awt.*;

public final class Scania extends Car implements ILoadable {

    /**
    * created the bed object
     */
    private final TiltableBed tiltableBed;

    /**
     * Sets parameters for Scania
     */
    public Scania(){
        super(
                false,
                 true,
                 Color.black,
                400,
                2,
                "Scania"
        );
        this.tiltableBed = new TiltableBed(this);
    }
    /**
     *
     * @return the current available acceleration
     */
    public double getSpeedFactor(){
        return (this.getEnginePower()*0.01);
    }
    /**
     * Sets the tilt of the bed.
     */
    public void setTilt(double amount){
        tiltableBed.setTilt(amount);
    }
    /**
     *
     * @return the current tilt of the bed
     */
    public double getAngle(){
        return tiltableBed.getAngle();
    }

    /**
     * Checks if the Vehicle can gas before doing so
     */

    /**
     * Overides the gas function with the requriment that the ramp is up
     * @param amount Specifies amount of which to increment speed.
     */
    @Override
    public void gas(double amount){
        if(InventoryHelper.gasAvailable(tiltableBed.getAngle())){
            super.gas(amount);
        }
    }
}
