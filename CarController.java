import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.

    private Timer timer = new Timer(delay, new CarController.TimerListener());

    // The frame that represents this instance View of the MVC pattern
    ICarDrawer frame;

    List<PairFix<Car,String>> carAndImagePaths = new ArrayList<>();

    //methods:



    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (PairFix<Car, String> carAndImagePath : carAndImagePaths) {
                Car car = carAndImagePath.getKey();
                String imagePath = carAndImagePath.getValue();
                double carFutureXcord=car.getXcord()+car.getCurrentSpeed()*MathHelper.roundCos(car.getDirection());
                double carFutureYcord=car.getYcord()-car.getCurrentSpeed()*MathHelper.roundSin(car.getDirection());

                //100 is the height of the picture
                if(carFutureXcord<0 || carFutureXcord>CarView.getWindowWidth()-100){
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();
                    System.out.println("TO CLOSE TO X");
                  //  collision(car);
                }
                else if(carFutureYcord<0 || carFutureYcord>CarView.getWindowHeight()-300){
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();
                    System.out.println("TO CLOSE TO Y");
                    //collision(car);
                }
                car.move();
                int x = (int) Math.round(car.getXcord());
                int y = (int) Math.round(car.getYcord());
                frame.updateSpeedLabel(car.getCurrentSpeed(),car.getModelName());
                frame.setImagePosition(x,y,imagePath);
                // repaint() calls the paintComponent method of the panel
                frame.repaint();
            }
        }
    }


    private List<Car> getCarsFromPairs(List<PairFix<Car,String>> carAndImagePaths){
        List<Car> cars = new ArrayList<>();
        for(PairFix<Car,String> elem : carAndImagePaths){
            cars.add(elem.getKey());
        }
        return cars;
    }

    void startTimer(){
        timer.start();
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Car car : getCarsFromPairs(carAndImagePaths)
                ) {
            car.gas(gasAmount);
        }
    }

    void brake(int amount){
        double brakeAmount = ((double) amount )/ 100;
        for (Car car : getCarsFromPairs(carAndImagePaths)
        ) {
            car.brake(brakeAmount);
        }
    }

    void setTurboOn(){
        for (Car car : getCarsFromPairs(carAndImagePaths)){
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOn();
                System.out.println(((Saab95) car).getTurbo());
            }
        }
    }
    void setTurboOff(){
        for (Car car : getCarsFromPairs(carAndImagePaths)){
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOff();
                System.out.println(((Saab95) car).getTurbo());
            }
        }
    }


    void liftBed(){
        for (Car car : getCarsFromPairs(carAndImagePaths)){
            if(car instanceof Scania){
                ((Scania) car).setTilt(70);
                System.out.println(((Scania) car).getAngle());
            }
        }
    }

    void lowerBed(){
        for (Car car : getCarsFromPairs(carAndImagePaths)){
            if(car instanceof Scania){
                ((Scania) car).setTilt(0);
                System.out.println(((Scania) car).getAngle());
            }
        }
    }

    void startAllCars(){
        for (Car car : getCarsFromPairs(carAndImagePaths)){
            car.startEngine();
        }
    }

    void stopAllCars(){
        for (Car car : getCarsFromPairs(carAndImagePaths)){
            car.stopEngine();
        }
    }

}














