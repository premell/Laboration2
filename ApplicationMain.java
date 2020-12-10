import javax.swing.*;

public class ApplicationMain {



    public static void main(String[] args) {
        // Instance of this class
        // The delay (ms) corresponds to 20 updates a sec (hz)


        //CarView now takes in a carController instead of the otherway around
        CarController carController = new CarController();

        carController.carAndImagePaths.add(new PairFix<Car, String>(new Volvo240(),"pics/Volvo240.jpg"));
        carController.carAndImagePaths.add(new PairFix<Car, String>(new Saab95(),"pics/Saab95.jpg"));
        carController.carAndImagePaths.add(new PairFix<Car, String>(new Scania(),"pics/Scania.jpg"));

        // Start a new view and send a reference of self
        carController.frame = new CarView("CarSim 0.0", carController);

        // Start the timer
        //Not sure if the timer should be in the controller
        carController.startTimer();
    }
}
