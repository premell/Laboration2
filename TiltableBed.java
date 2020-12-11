public final class TiltableBed{

    private double angle;
    private Scania bedOwner;

    /**
     * Set the parameters for the tiltable bed
     * @param bedOwner the owner of the bed
     */
    public TiltableBed(Scania bedOwner) {
        this.angle=0;
        this.bedOwner=bedOwner;
    }

    /**
     * Sets the tilt of the bed
     * @param amount how much the bed should tilt
     */
    public void setTilt(double amount){
        if(amount>70 || amount<0){
            System.out.println("Tilt can only be between 0 and 70 degrees");
            return;
        }
        if(bedOwner.getCurrentSpeed()>0.01 && amount >0.01){
            System.out.println("Tilt angle grater than 0 may only be set if the car is stationary.");
            return;
        }
        angle=amount;
    }

    /**
     * @return the current angle of the bed
     */
    public double getAngle(){
        return angle;
    }
}