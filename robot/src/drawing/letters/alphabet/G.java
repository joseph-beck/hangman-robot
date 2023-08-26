package drawing.letters.alphabet;

import drawing.Marker;
import drawing.letters.Letter;

/**
 * This is the object "G" which implements the Letter class.
 * 
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023 
 */
public class G implements Letter {
    /**
     * This creates a final character.
     */
    private final char characater;

    /**
     * The constructor assigns the letter "G" to character.
     * @return none
     */
    public G() {
        this.characater = 'G';
    }

    /**
    * Draw method with logic using a Marker instance.
    * 
    * @return none
    */
    @Override
    public void draw() {
        Marker.calibrate();
        Marker.markerVertical(-100);
        Marker.markerHorizontal(250);
        Marker.markerDown();
        Marker.markerHorizontal(-250);
        Marker.markerVertical(100);
        Marker.markerHorizontal(230);
        Marker.markerVertical(-50);
        Marker.markerHorizontal(90);
        Marker.markerHorizontal(-140);
        Marker.markerUp();
    }

    /**
    *  Draw method which takes in an integer to call the offset method.
    * 
    * @param int offset
    * @return none
    */
    @Override
    public void draw(int offset) {
        offset(offset);
        draw();
    }

    /**
    *  Offset method which takes in an integer and is required in drawing the letter at the correct Horizontal axis.
    * 
    * @param int offset
    * @return none
    */
    @Override
    public void offset(int offset) {
        Marker.markerDown();
		Marker.calibrate();
        Marker.markerHorizontal(offset);
    }

    /**
    *  This returns the character.
    * 
    * @return char
    */
    @Override
    public char getChar() {
        return this.characater;
    }
}