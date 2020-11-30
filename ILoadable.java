public interface ILoadable {

    public double getXcord();

    public double getYcord();

    public boolean getCarSized();

    public boolean getTruckSized();

    public void setCurrentlyTransported();

    public void setPositionDuringTransport(double xCord, double yCord);

    public void dropOff();


}
