package drawing.hangman.stages;

import drawing.Marker;
import drawing.hangman.Stage;

/**
 * The Legs object implements the the Stage class.
 * This draws out the Legs of the hangman drawing.
 * 
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023 
 */
public class Legs implements Stage {
    /**
     * The stage at which the drawings are at.
     */
    private int stageNumber;

    /**
     * Constructor to intialise the stageNumber.
     * 
     * @return none
     */
    public Legs() {
        this.stageNumber = 7;
    }

    /**
     * Draw method with logic using a Marker instance.
     * 
     * @return none
     */
    @Override
    public void draw() {
        Marker.calibrate();
       	Marker.markerVertical(-200);
   	 	Marker.markerHorizontal(250);
   		Marker.markerVertical(140);
   		Marker.markerDown();
   		Marker.markerDiagonalLR(40,40);
   		Marker.calibrate();
   		Marker.markerHorizontal(-50);
   		Marker.markerVertical(-65);
   		Marker.markerDown();
   		Marker.markerDiagonalRL(40,40);
        Marker.markerUp();
    }

    /**
     * This returns the stageNumber we are at.
     * 
     * @return int
     */
    @Override
    public int getStageNumber() {
        return this.stageNumber;
    }
}