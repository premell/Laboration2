public interface ICarDrawer {
    void repaint();

    void setImagePosition(int x, int y, String imagePath);

    void updateSpeedLabel(double speed, String name);
}
