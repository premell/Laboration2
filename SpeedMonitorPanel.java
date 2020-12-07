import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SpeedMonitorPanel extends JPanel {

    /*
    Som svar på frågorna på Canvas:

    Q: Hur bör MVC lösning vara utformad?

    A: Denna panel bör vara en självstående klass som CarView skapar en
    instans av, precis som DrawPanel. CarView har ett beroende av denna SpeedMonitorPanel,
    men SpeedMonitorPanel har inget beroende av någon annan klass. På det viset kan
    SpeedMonitorPanel användas utan ytterligare beroenden och kan koexistera med vilken
    annan panel som helst.

    Q: Hur bör de olika komponenterna kommunicera med varandra?

    A: Som sagt är CarView beroende av SpeedMonitorPanel, så den kommer sätta storlek och position
    av denna panel, men SpeedMonitorPanel tar inga parametrar eller information någon annan stans ifrån.
    Det enda SpeedMonitorPanel gör är att sätta hastighetsmätare enligt updateSpeedLabel
    som görs ifrån CarView(som får kommando från CarController).
     */

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
