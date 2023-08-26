package drawing.letters.alphabet;

import drawing.Marker;
import drawing.letters.Letter;

/**
 * This is the object "Q" which implements the Letter class.
 * 
 * @author redacted
 * @version 0.1
 * @since 2023 
 */
public class Q implements Letter {
    /**
     * This creates a final character.
     */
    private final char characater;

    /**
     * The constructor assigns the letter "Q" to character.
     * @return none
     */
    public Q() {
        this.characater = 'Q';
    } 

    /**
    *  Draw method which takes in an integer to call the offset method.
    * 
    * @return none
    */
    @Override
    public void draw() {
        Marker.calibrate();
	  	Marker.markerDown();
 	    Marker.markerVertical(-110);
	  	Marker.markerHorizontal(250);
	  	Marker.markerVertical(110);
	  	Marker.markerHorizontal(-250);
	  	Marker.markerUp();
	  	Marker.markerVertical(-50);
	  	Marker.markerHorizontal(200);
    	Marker.markerDown();
	   	Marker.markerDiagonalLR(70,70);
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