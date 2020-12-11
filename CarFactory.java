public class CarFactory {


    public static Car getCar(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("VOLVO240")){
            return new Volvo240();

        } else if(shapeType.equalsIgnoreCase("SAAB95")){
            return new Saab95();

        } else if(shapeType.equalsIgnoreCase("SCANIA")){
            return new Scania();
        }
        return null;
    }


}
