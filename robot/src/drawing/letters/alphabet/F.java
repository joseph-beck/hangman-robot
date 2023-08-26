package drawing.letters.alphabet;

import drawing.Marker;
import drawing.letters.Letter;

/**
 * This is the object "F" which implements the Letter class.
 * 
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023 
 */
public class F implements Letter {
    /**
     * This creates a final character.
     */
    private final char characater;

    /**
     * The constructor assigns the letter "F" to character.
     * @return none
     */
    public F() {
        this.characater = 'F';
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
	 	Marker.markerHorizontal(250);
	 	Marker.markerUp();
	    Marker.markerHorizontal(-250);
	    Marker.markerVertical(50);
	    Marker.markerDown();
	    Marker.markerHorizontal(250);
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