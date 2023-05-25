package drawing.letters.alphabet;

import drawing.Marker;
import drawing.letters.Letter;

/**
 * This is the object "D" which implements the Letter class.
 * 
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023 
 */
public class D implements Letter {
    /**
     * This creates a final character.
     */
    private final char characater;

    /**
     * The constructor assigns the letter "D" to character.
     * @return none
     */
    public D() {
        this.characater = 'D';
    }

    /**
    * Draw method with logic using a Marker instance.
    * 
    * @return none
    */
    @Override
    public void draw() {
        Marker.calibrate();
        Marker.markerDown();
        Marker.markerVertical(-110);
        Marker.markerHorizontal(200);
        Marker.markerDiagonalLR(55,55);
        Marker.markerDiagonalRL(55,55);
        Marker.markerHorizontal(-200);
        Marker.markerUp();
    }
    /**
    *  Draw method which takes in an integer to call the offset method.
    * 
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