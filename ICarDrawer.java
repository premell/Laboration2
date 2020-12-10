import javax.swing.*;
import java.util.List;

public interface ICarDrawer {
    void repaint();

    void setImagePosition(int x, int y, String imagePath);

    void updateSpeedLabel(double speed, String name);

    int getWindowWidth();
    int getWindowHeight();

    //methods for panels
    //not sure if this should be here or another interface
    void addBottomPanel(String title, List<JButton> buttons );
    void addGasPanel(String title, JSpinner gasSpinner);


}
