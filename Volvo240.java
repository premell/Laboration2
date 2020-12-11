import java.awt.*;

/**
 * non abstract class Volvo240 extends Car with added specific parameters to the car model.
 */
public final class Volvo240 extends Car implements ILoadable {

    private final static double trimFactor = 1.25; //multiplier that affects speedFactor
    private boolean isBeingTransported;

    /**
     * Sets parameters for Volvo240.
     */
    public Volvo240() {
        super(
                true,
                false,
                Color.black,
                100,
                4,
                "Volvo 240"
        );
        stopEngine();
    }

    /**
     * speedFactor is one percent of enginePower multiplied by the trimFactor.
     * @return speedFactor
     */
    public double getSpeedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}
