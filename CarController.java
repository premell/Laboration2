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

    // protected ICarDrawer frame;

    private List<ICarDrawer> observers;

    protected static List<PairFix<String,String>> allPossibleCars = new ArrayList<>();
    private CarControlButtons controlButtons;
    private JSpinner gasSpinner;
    private Timer timer = new Timer(delay, new CarController.TimerListener());


    private CarModel carModel;

    private int inputAmount;

    //methods:



    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CarModel.moveCars();
        }
    }


    public void addObserver(ICarDrawer observer){
        observers.add(observer);
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
            startButton.setPreferredSize(new Dimension(CarModel.frameWidth / 5 - 15, 200));

            stopButton.setBackground(Color.red);
            stopButton.setForeground(Color.black);
            stopButton.setPreferredSize(new Dimension(CarModel.frameWidth / 5 - 15, 200));
        }
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
        CarModel.gas(amount);
    }

    void brake(int amount) {
        CarModel.brake(amount);
    }

    void setTurboOn(){
        CarModel.setTurboOn();
    }

    void setTurboOff(){
       CarModel.setTurboOff();
    }

    void liftBed(){
      CarModel.liftBed();
    }

    void lowerBed(){
        CarModel.lowerBed();
    }

    void startAllCars(){
        CarModel.startAllCars();
    }

    void stopAllCars(){
        CarModel.stopAllCars();
    }

    void addCar(){
        Random rand = new Random();
        PairFix<String, String> carToAdd = allPossibleCars.get(rand.nextInt(allPossibleCars.size()));
        CarModel.addCar(new PairFix<Car,String>(CarFactory.getCar(carToAdd.getKey()),carToAdd.getValue()));
    }

    void removeCar(){
        CarModel.removeCar();
    }

    void printPossibleCars(){
        System.out.println(allPossibleCars);

    }

}














