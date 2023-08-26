package behaviors;

import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import drawing.Marker;
import robot.*;


/**
 * BackUp implements Behavior in order to back the robot up until it hits a black line.
 * This is used for calibratoin and ensuring the robot draws things in the right place.
 * 
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023 
 */
public class BackUp implements Behavior {

	/**
	 * Stores whether or not this is the startUp backup.
	 */
	private boolean startUp;

	/**
	 * Initializes the BackUp object.
	 * Gets instance of the singletons it requires in case they are null.
	 * 
	 * @return none
	 */
	public BackUp() {
		this.startUp = true;
        Marker.getInstance();
		Sensors.getInstance();
	}

	/**
	 * Implements the action method of behavior.
	 * Using a sample provider it checks the light level of the sensor to see when near the marker line.
	 * It uses the motors to go backwards until this point is reached.
	 * 
	 * @return none
	 */
	@Override
	public void action() {
		SampleProvider colourProvider = Sensors.getColourProvider();
		float[] samplesColour = new float[1];
	    boolean seen = false;
        int tachoCount = 0;
	    BehaviorData.setTachoCount(0);

        while(!seen ) {
			tachoCount = Marker.markerGetTachoCount();
            Marker.markerBackwards();
        	colourProvider.fetchSample(samplesColour, 0);

          	if(samplesColour[0] < 0.025f && tachoCount < -100) {
				seen = true;
				Marker.markerStop();

				if (startUp) {
					startUp = false;
					StateHandler.setState(States.START_UP);
				} else {
					BehaviorData.setPermanentTachoCount(tachoCount);
					StateHandler.setState(States.GUESSING);
				}
         	}
        }
	}

	/**
	 * Supress function does nothing here.
	 * 
	 * @return none
	 */
	@Override
	public void suppress() { }

	/**
	 * Takes control when the state is equal to the BACK_UP state.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean takeControl() {
		return StateHandler.getState() == States.BACK_UP;
	}
}