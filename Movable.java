/**
 * Template for any object that can move
 */
public interface Movable {
    /**
     * Moves the car. Direction and distance is specified by subclass.
     */
    void move();

    /**
     * Turns the direction of the car. Affects subsequent uses of move().
     */
    void turnLeft();

    /**
     * Turns the direction of the car. Affects subsequent uses of move().
     */
    void turnRight();
}