import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JFrame implements ICarDrawer{
    private static final int windowWidth = 1000;
    private static final int windowHeight = 1000;

    public int getWindowWidth(){
        return windowWidth;
    }
    public int getWindowHeight(){
        return windowHeight;
    }

    // The controller member
    CarController carController;

    DrawPanel drawPanel = new DrawPanel(windowWidth-200, windowHeight -300);
    SpeedMonitorPanel speedMonitorPanel = new SpeedMonitorPanel(180,windowHeight -300);

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    int inputAmount = 0;
    JLabel gasLabel = new JLabel("Amount");

    // Constructor
    public CarView(String framename, CarController cc){
        this.carController = cc;
        initComponents(framename);
    }

    public void updateSpeedLabel(double speed, String name){
        speedMonitorPanel.updateSpeedLabel(speed,name);
    }

    //Sets the position of a car image on the DrawPanel
    public void setImagePosition(int x, int y, String imagePath){
        drawPanel.setImagePosition(x,y,imagePath);
    }

    public void addBottomPanel(String title,  List<JButton> buttons ){
        this.setTitle(title);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 4));
        bottomPanel.setPreferredSize(new Dimension((windowWidth /2)+4, 200));
        bottomPanel.setBackground(Color.CYAN);

        for(JButton button:buttons)
            bottomPanel.add(button);

        this.add(bottomPanel);
    }

    public void addGasPanel(String title, JSpinner gasSpinner){
       this.setTitle(title);

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);
        this.add(speedMonitorPanel);


/*
        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((windowWidth /2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(windowWidth /5-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(windowWidth /5-15,200));
        this.add(stopButton);

        // This actionListener is for the gas button only
        // TODO: Create more for each component as necessary
        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.gas(inputAmount);
            }
        });
        brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.brake(inputAmount);
            }
        });

        //Saab buttons
        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.setTurboOn();
            }
        });
        turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.setTurboOff();
            }
        });


        //Scania buttons
       liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.liftBed();
            }
        });
       lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.lowerBed();
            }
        });
        //All cars
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.startAllCars();
            }
        });

       stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carController.stopAllCars();
            }
        });

 */
        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}