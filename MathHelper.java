public class MathHelper {

    public static double roundCos(int angle){
        if(angle==90 || angle==270){
            return 0;
        }else if(angle==180){
            return -1;
        }else if(angle==0){
            return 1;
        }else{
            return Math.cos(Math.toRadians(angle));
        }
    }

    public static double roundSin(int angle){
        if(angle==180 || angle==0){
            return 0;
        }else if(angle==270){
            return -1;
        }else if(angle==90){
            return 1;
        }else{
            return Math.sin(Math.toRadians(angle));
        }
    }


}
