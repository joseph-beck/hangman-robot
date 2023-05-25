package drawing.hangman.stages;

import drawing.Marker;
import drawing.hangman.Stage;

/**
* The Body object implements the the Stage class.
 * This draws out the body of the hangman drawing.
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023 
 */
public class Body implements Stage {
    /**
     * The stage at which the drawings are at.
     */
    private int stageNumber;

    /**
     * Constructor to intialise the stageNumber.
     * 
     * @return none
     */
    public Body() {
        this.stageNumber = 5;
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
 	 	Marker.markerVertical(65);
 		Marker.markerDown();
 	 	Marker.markerVertical(90);
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