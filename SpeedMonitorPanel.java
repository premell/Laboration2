import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SpeedMonitorPanel extends JPanel {

    private int maxSpeedLabels=5;
    List<JLabel> speedIndicators = new ArrayList<>();
    private int modifyingLabelIndex;


    public SpeedMonitorPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.gray);
        this.setLayout(new GridLayout(5,1));
        for(int i=0;i<maxSpeedLabels;i++){
            speedIndicators.add(new JLabel());
            this.add(speedIndicators.get(i));
        }
    }

    public void updateSpeedLabel(double speed, String name){
        speedIndicators.get(modifyingLabelIndex).setText(name + ": " + speed);
        modifyingLabelIndex++;
    }

    @Override
    protected void paintComponent(Graphics g) {
        modifyingLabelIndex =0;
        super.paintComponent(g);
    }
}
