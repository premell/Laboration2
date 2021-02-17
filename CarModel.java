import java.util.ArrayList;
import java.util.List;

public class CarModel {


    protected static List<PairFix<Car,String>> carAndImagePaths = new ArrayList<>();
    private static List<ICarDrawer> observers = new ArrayList<>();
    public static final int frameWidth=1000;
    public static final int frameHeight=1000;

    public static void moveCars() {
        if (carAndImagePaths.size() == 0) {
            //frame.repaint();
            //repaintCars();
            return;
        }
        for (PairFix<Car, String> carAndImagePath : carAndImagePaths) {
            Car car = carAndImagePath.getKey();
            String imagePath = carAndImagePath.getValue();
            double carFutureXcord = car.getXcord() + car.getCurrentSpeed() * MathHelper.roundCos(car.getDirection());
            double carFutureYcord = car.getYcord() - car.getCurrentSpeed() * MathHelper.roundSin(car.getDirection());

            //100 is the height of the picture
            if (carFutureXcord < 0 || carFutureXcord > frameWidth - 100) {
                car.turnLeft();
                car.turnLeft();
                car.startEngine();
                System.out.println("TO CLOSE TO X");
                //  collision(car);
            } else if (carFutureYcord < 0 || carFutureYcord > frameHeight - 300) {
                car.turnLeft();
                car.turnLeft();
                car.startEngine();
                System.out.println("TO CLOSE TO Y");
                //collision(car);
            }
            car.move();

            updateObservers(car, imagePath);
            // repaint() calls the paintComponent method of the panel
            //frame.repaint();
        }
    }

    private static void updateObservers(Car car, String imagePath){
        int x = (int) Math.round(car.getXcord());
        int y = (int) Math.round(car.getYcord());
        for (ICarDrawer observer: observers){
            observer.updateSpeedLabel(car.getCurrentSpeed(), car.getModelName());
            observer.setImagePosition(x, y, imagePath);
            observer.repaint();
        }
    }

    public static void addObserver(ICarDrawer observer){
        observers.add(observer);
    }

    public static void addCar(PairFix<Car,String> car){
        if(carAndImagePaths.size()>10)
            return;
        carAndImagePaths.add(car);
    }

    public static void removeCar(){
        if(carAndImagePaths.size()<=0)
            return;
        carAndImagePaths.remove(carAndImagePaths.size()-1);
    }

    public static void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Car car : getCarsFromPairs(carAndImagePaths)
        ) {
            car.gas(gasAmount);
        }
    }

    public static void brake(int amount){
        double brakeAmount = ((double) amount )/ 100;
        for (Car car : getCarsFromPairs(carAndImagePaths)
        ) {
            car.brake(brakeAmount);
        }
    }

    public static void setTurboOn(){
        for (Car car : getCarsFromPairs(carAndImagePaths)){
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOn();
                System.out.println(((Saab95) car).getTurbo());
            }
        }
    }
    public static void setTurboOff(){
        for (Car car : getCarsFromPairs(carAndImagePaths)){
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOff();
                System.out.println(((Saab95) car).getTurbo());
            }
        }
    }


    public static void liftBed(){
        for (Car car : getCarsFromPairs(carAndImagePaths)){
            if(car instanceof Scania){
                ((Scania) car).setTilt(70);
                System.out.println(((Scania) car).getAngle());
            }
        }
    }

    public static void lowerBed(){
        for (Car car : getCarsFromPairs(carAndImagePaths)){
            if(car instanceof Scania){
                ((Scania) car).setTilt(0);
                System.out.println(((Scania) car).getAngle());
            }
        }
    }

    public static void startAllCars(){
        for (Car car : getCarsFromPairs(carAndImagePaths)){
            car.startEngine();
        }
    }

    public static void stopAllCars(){
        for (Car car : getCarsFromPairs(carAndImagePaths)){
            car.stopEngine();
        }
    }

    private static List<Car> getCarsFromPairs(List<PairFix<Car,String>> carAndImagePaths){
        List<Car> cars = new ArrayList<>();
        for(PairFix<Car,String> elem : carAndImagePaths){
            cars.add(elem.getKey());
        }
        return cars;
    }


}

