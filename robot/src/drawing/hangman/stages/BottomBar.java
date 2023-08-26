package drawing.hangman.stages;

import drawing.Marker;
import drawing.hangman.Stage;

/**
 * The BottomBar object implements the the Stage class.
 * This draws out the Bottom Bar of the hangman drawing.
 * 
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023 
 */
public class BottomBar implements Stage {
    /**
     * The stage at which the drawings are at.
     */
    private int stageNumber;

    /**
     * Constructor to intialise the stageNumber.
     * 
     * @return none
     */
    public BottomBar() {
        this.stageNumber = 1;
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
		Marker.markerDown();
		Marker.markerHorizontal(600);
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