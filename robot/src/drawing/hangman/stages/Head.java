package drawing.hangman.stages;

import drawing.Marker;
import drawing.hangman.Stage;

/**
 *  The Head object implements the the Stage class.
 * This draws out the Head of the hangman drawing.
 * 
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023 
 */
public class Head implements Stage {
    /**
     * The stage at which the drawings are at.
     */
    private int stageNumber;

    /**
     * Constructor to intialise the stageNumber.
     * 
     * @return none
     */
    public Head() {
        this.stageNumber = 4;
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
	 	Marker.markerHorizontal(175);
        Marker.markerHorizontal(150);
		Marker.markerDown();
	 	Marker.markerVertical(65);
         Marker.markerHorizontal(-150);
	 	Marker.markerVertical(-65);
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