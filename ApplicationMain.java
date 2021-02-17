import javax.swing.*;

public class ApplicationMain {



    public static void main(String[] args) {
        // Instance of this class
        // The delay (ms) corresponds to 20 updates a sec (hz)


        //CarView now takes in a carController instead of the otherway around
        CarController carController = new CarController();

        //All cars that is possible to add with the "add car button"
        carController.allPossibleCars.add(new PairFix<String, String>("VOLVO240","pics/Volvo240.jpg"));
        carController.allPossibleCars.add(new PairFix<String, String>("SAAB95","pics/Saab95.jpg"));
        carController.allPossibleCars.add(new PairFix<String, String>("SCANIA","pics/Scania.jpg"));

        CarModel.carAndImagePaths.add(new PairFix<Car, String>(CarFactory.getCar("VOLVO240"),"pics/Volvo240.jpg"));
        CarModel.carAndImagePaths.add(new PairFix<Car, String>(CarFactory.getCar("SAAB95"),"pics/Saab95.jpg"));
        CarModel.carAndImagePaths.add(new PairFix<Car, String>(CarFactory.getCar("SCANIA"),"pics/Scania.jpg"));

        // Start a new view and send a reference of self
        ICarDrawer frame = new CarView("CarSim 0.0", carController);
        frame.addGasPanel("Gas Panel", carController.getGasSpinner());
        frame.addBottomPanel("Bottom Panel", carController.getCarControlButtons());
        CarModel.addObserver(frame);

        //carController.controllPanel = new ControlPanel("Panel 1", carController);

        // Start the timer
        //Not sure if the timer should be in the controller
        carController.startTimer();
    }
}
