package drawing;

/**
 * This is a signleton for the Pen object .
 * @author Joseph Beck
 * @version 0.1
 * @since 2023 
 */
public final class Marker {
    /**
     * Stores the Marker instance.
     */
    private static Marker instance;
    /**
     * Stores the Pen object.
     */
    private static Pen pen;
    /**
     * Stores the boolean of closed.
     */
    private static boolean closed;

    /**
     * Constructor that intilialises the Pen object and boolean
     * 
     * @return none
     */
    private Marker() {
        pen = new Pen();
        closed = false;
    }

    /**
     * This gets the instance of Marker and returns it.
     * Checks if its null, which createds a new Instance.
     * 
     * @return none
     */
    public static Marker getInstance() {
        if (instance == null) instance = new Marker();

        return instance;
    }

    /**
     * Method which gets the instances' of the Motor and Sensor singletons.
     * 
     * @return none
     */
    public static void markerOpen() {
        if (closed) pen.open();
    }

    /**
     * Closes all motors and sensors.
     * 
     * @return none
     */
    public static void markerClose() {
        if (!closed) pen.close();
    }

    /**
     * This uses the touchSensor to calibrate the pen on its Vertical axis as well putting the pen back up. 
     * This is done by moving the motor until it touchs the sensor.
     * 
     * @return none
     */
    public static void calibrate() {
        pen.calibrate();
    }

    /**
     * This puts the pen up by rotating it positvely.
     * 
     * @return none
     */
    public static void markerUp() {
        pen.penUp();
    }

    /**
     * This puts the pen down by rotating it negitvely.
     * 
     * @return none
     */
    public static void markerDown() {
        pen.penDown();
    }

    /**
     * This moves the pen vertically by the horizontal rate.
     * 
     * @return none
     */
    public static void markerVertical(int horizontalRotate) {
        pen.penVertical(horizontalRotate);
    }

    /**
     * This moves the robot backwards.
     * 
     * @return none
     */
    public static void markerBackwards() {
        pen.penBackwards();
    }

    /**
     * This stops the motor from moving forwards.
     * 
     * @return none
     */
    public static void markerStop() {
        pen.penStop();
    }

    /**
     * This moves the pen Horizontally by the forwards rate.
     * 
     * @return none
     */
    public static void markerHorizontal(int forwardsRotate) {
        pen.penHorizontal(forwardsRotate);
    }

    /**
     * This uses a horizontal and forwards input inorder to draw from Left to 
     * Right diagonally by synching the motors and moving them at the same time.
     * 
     * @return none
     */
    public static void markerDiagonalLR(int horizontalRotate, int fowardsRotate) {
        pen.penDiagonalLR(horizontalRotate, fowardsRotate);
    }
    
    /**
     * This uses a horizontal and forwards input inorder to draw from Left to 
     * Right diagonally by synching the motors and moving them at the same time.
     * 
     * @return none
     */
    public static void markerDiagonalRL(int horizontalRotate, int forwardsRotate) {
        pen.penDiagonalRL(horizontalRotate, forwardsRotate);
    }
 
    /**
     * This checks if the angle of gyroSensor past a certain threshold.
     * 
     * @return boolean
     */
    public static boolean markerBeepGyro() {
        return pen.beepGyro();
    }

    /**
     * Returns the tachoCount of the forwards motor
     * 
     * @return int
     */
    public static int markerGetTachoCount() {
        return pen.tachoCount();
    }

    /**
     * This method causes the robot to move backwards up until the 
     * colourSensor detects a black line past a certain TachoCount threshold.
     * 
     * @return none
     */
    public static void markerBackUp() {
        pen.backUpPen();
    }
}