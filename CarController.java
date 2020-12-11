import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    // The frame that represents this instance View of the MVC pattern
    protected ICarDrawer frame;

    private CarControlButtons controlButtons;
    private JSpinner gasSpinner;
    private Timer timer = new Timer(delay, new CarController.TimerListener());
    protected List<PairFix<Car,String>> carAndImagePaths = new ArrayList<>();
    protected List<PairFix<Car,String>> allPossibleCars = new ArrayList<>();

    private int inputAmount;

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
                if(carFutureXcord<0 || carFutureXcord>frame.getWindowWidth()-100){
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();
                    System.out.println("TO CLOSE TO X");
                  //  collision(car);
                }
                else if(carFutureYcord<0 || carFutureYcord>frame.getWindowHeight()-300){
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
    private class GasSpinner{
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step

        JSpinner gasSpinner = new JSpinner(spinnerModel);

        public GasSpinner(){
            this.gasSpinner = gasSpinner;
            addMethodToSpinner();
        }

        void addMethodToSpinner(){
            gasSpinner.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    inputAmount = (int) ((JSpinner)e.getSource()).getValue();
                }
            });
        }
    }

    private class CarControlButtons{

        List<JButton> buttons = new ArrayList<>();

        JButton gasButton = new JButton("Gas");
        JButton brakeButton = new JButton("Brake");
        JButton turboOnButton = new JButton("Saab Turbo on");
        JButton turboOffButton = new JButton("Saab Turbo off");
        JButton liftBedButton = new JButton("Scania Lift Bed");
        JButton lowerBedButton = new JButton("Lower Lift Bed");

        JButton startButton = new JButton("Start all cars");
        JButton stopButton = new JButton("Stop all cars");

        JButton addCarButton= new JButton("Add new car");
        JButton removeCarButton = new JButton("Remove last added car");

        public CarControlButtons(){
            addButtonsToList();
            addMethodsToButtons();
            stylizeButtons();
        }


        void addButtonsToList() {
            buttons.add(gasButton);
            buttons.add(brakeButton);
            buttons.add(turboOnButton);
            buttons.add(turboOffButton);
            buttons.add(liftBedButton);
            buttons.add(lowerBedButton);

            buttons.add(startButton);
            buttons.add(stopButton);

            buttons.add(addCarButton);
            buttons.add(removeCarButton);
        }
        void addMethodsToButtons() {
            // This actionListener is for the gas button only
            // TODO: Create more for each component as necessary
            gasButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gas(inputAmount);
                }
            });
            brakeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    brake(inputAmount);
                }
            });

            //Saab buttons
            turboOnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setTurboOn();
                }
            });
            turboOffButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setTurboOff();
                }
            });

            //Scania buttons
            liftBedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    liftBed();
                }
            });
            lowerBedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lowerBed();
                }
            });
            //All cars
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startAllCars();
                }
            });
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stopAllCars();
                }
            });

            addCarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addCar();
                }
            });
            removeCarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeCar();
                }
            });

        }

        void stylizeButtons() {
            startButton.setBackground(Color.blue);
            startButton.setForeground(Color.green);
            startButton.setPreferredSize(new Dimension(frame.getWindowWidth() / 5 - 15, 200));

            stopButton.setBackground(Color.red);
            stopButton.setForeground(Color.black);
            stopButton.setPreferredSize(new Dimension(frame.getWindowWidth() / 5 - 15, 200));

        }
    }

    private List<Car> getCarsFromPairs(List<PairFix<Car,String>> carAndImagePaths){
        List<Car> cars = new ArrayList<>();
        for(PairFix<Car,String> elem : carAndImagePaths){
            cars.add(elem.getKey());
        }
        return cars;
    }

    //Not sure if this should be here
    void startTimer(){
        timer.start();
    }

    JSpinner getGasSpinner(){
        GasSpinner gasSpinner = new GasSpinner();
        return gasSpinner.gasSpinner;
    }

    List<JButton> getCarControlButtons(){
        CarControlButtons controlButtons = new CarControlButtons();
        return controlButtons.buttons;
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

    void addCar(){
        if(carAndImagePaths.size()>=10)
            return;

        Random rand = new Random();
        PairFix<Car, String> carToAdd = allPossibleCars.get(rand.nextInt(allPossibleCars.size()));
        carAndImagePaths.add(carToAdd);
    }

    void removeCar(){
        carAndImagePaths.remove(carAndImagePaths.size()-1);
    }

    void printPossibleCars(){
        System.out.println(allPossibleCars);

    }

}














