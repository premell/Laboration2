import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize

    BufferedImage volvoImage;
    BufferedImage SaabImage;
    BufferedImage ScaniaImage;


    // To keep track of a singel cars position
    //Point volvoPoint = new Point();

    ArrayList<Point> imagePoints = new ArrayList<>();
    ArrayList<String> imageFilePaths = new ArrayList<>();
    ArrayList<BufferedImage> bufferedImages = new ArrayList<>();

    // TODO: Make this genereal for all cars
    void setImagePosition(int x, int y, String imageFilePath){
        Point point = new Point(x,y);
        imagePoints.add(point);
        imageFilePaths.add(imageFilePath);
        System.out.println("Adding Image point");

        try {
            /*for(String elem : imageFilePaths){
                bufferedImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream(elem)));
                System.out.println("Adding buffered images");
            }*/
            bufferedImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream(imageFilePath)));

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.

            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            SaabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            ScaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("This many imagePoints "+imagePoints.size());
        for(int i = 0 ; i < imagePoints.size() ; i++){
            g.drawImage(bufferedImages.get(i), (int)Math.round(imagePoints.get(i).getX())+100*i, (int)Math.round(imagePoints.get(i).getY()), null);
            System.out.println("Ska vara 3" + bufferedImages.size());
        }
        imagePoints.clear();
        imageFilePaths.clear();
        bufferedImages.clear();
        System.out.println("Cleared arraylists");
        /*g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null);
        g.drawImage(SaabImage, 100+volvoPoint.x, 0+volvoPoint.y, null);
        g.drawImage(ScaniaImage, 200+volvoPoint.x, 0+volvoPoint.y, null);*/

        // see javadoc for more info on the parameters
    }

    protected void nextFrame(){
        System.out.println("clearing");
        imagePoints.clear();
        imageFilePaths.clear();
        bufferedImages.clear();
    }
}
