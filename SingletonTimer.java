import javax.swing.*;
import java.awt.event.ActionListener;

public class SingletonTimer{
    private static Timer timer;

    private SingletonTimer(){}

    public static Timer initTimer(int delay,ActionListener listener){
        if(timer==null){
            timer=new Timer(delay,listener);
            return timer;
        }
        timer.setDelay(delay);
        for(ActionListener e : timer.getActionListeners()){
            timer.removeActionListener(e);
        }
        timer.addActionListener(listener);
        return timer;
    }

}
