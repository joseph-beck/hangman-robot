package drawing.hangman.stages;

import drawing.Marker;
import drawing.hangman.Stage;

/**
 * The TopBar object implements the the Stage class.
 * This draws out the Top Bar of the hangman drawing.
 * 
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023 
 */
public class TopBar implements Stage {
    /**
     * The stage at which the drawings are at.
     */
    private int stageNumber;

    /**
     * Constructor to intialise the stageNumber.
     * 
     * @return none
     */
    public TopBar() {
        this.stageNumber = 3;
    }

    /**
     * Draw method with logic using a Marker instance.
     * 
     * @return none
     */
    @Override
    public void draw() {
        Marker.calibrate();
		Marker.markerHorizontal(100);
		Marker.markerVertical(-200);
		Marker.markerDown();
    	Marker.markerHorizontal(500);
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