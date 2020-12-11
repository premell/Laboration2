import java.awt.*;

/**
 * non abstract class Saab95 extends Car with added specific parameters to the car model.
 */
public final class Saab95 extends Car implements ILoadable {

    private boolean turboOn; // Determines turbo on if true, off otherwise.

    /**
     * Sets parameters for saab95.
     */
    public Saab95(){
        super(
                true,
            false,
            Color.red,
            125,
            2,
            "Saab 95"
        );
	    turboOn = false;
        stopEngine();
    }

    /**
     * Getter for turbo status
     * @return turboOn x
     */
    public boolean getTurbo(){
        return turboOn;
    }

    /**
     * Sets turboOn to true.
     */
    public void setTurboOn(){
	    turboOn = true;
    }

    /**
     * Sets turboOn to false.
     */
    public void setTurboOff(){
	    turboOn = false;
    }

    /**
     * speedFactor is one percent of enginePower multiplied by the turbo.
     * @return speedFactor
     */
    public double getSpeedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower()* 0.01 * turbo;
    }

}
