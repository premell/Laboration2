import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Saab95());
        cc.cars.add(new Volvo240());
        cc.cars.add(new Scania());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                double carFutureXcord=car.getXcord()+car.getCurrentSpeed()*MathHelper.roundCos(car.getDirection());
                double carFutureYcord=car.getYcord()-car.getCurrentSpeed()*MathHelper.roundSin(car.getDirection());


                if(carFutureXcord<0 || carFutureXcord>CarView.getWindowWidth()-100){
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();
                    System.out.println("TO CLOSE TO X");
                  //  collision(car);
                }
                else if(carFutureYcord<0 || carFutureYcord>CarView.getWindowHeight()-60){
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();
                    System.out.println("TO CLOSE TO Y");
                    //collision(car);
                }
                car.move();
                int x = (int) Math.round(car.getXcord());
                int y = (int) Math.round(car.getYcord());
                frame.drawPanel.moveit(x,y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }


    // Calls the gas method for each car once
    void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Car car : cars
                ) {
            car.gas(gasAmount);
        }
    }

    void brake(int amount){
        double brakeAmount = ((double) amount )/ 100;
        for (Car car : cars
        ) {
            car.brake(brakeAmount);
        }
    }

    void liftBed(){
        for (Car car : cars){
            if(car instanceof Scania){
                ((Scania) car).setTilt(70);
                System.out.println(((Scania) car).getAngle());
            }
        }
    }

    void lowerBed(){
        for (Car car : cars){
            if(car instanceof Scania){
                ((Scania) car).setTilt(0);
                System.out.println(((Scania) car).getAngle());
            }
        }
    }

    void startAllCars(){
        for (Car car : cars){
            car.startEngine();
        }
    }

    void stopAllCars(){
        for (Car car : cars){
            car.stopEngine();
        }
    }

}














