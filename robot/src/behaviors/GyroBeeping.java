package behaviors;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import robot.*;
import client.SingleClient;
import drawing.Marker;

/**
 * Checks whether or not the robot is out of alignment, if it doesbeeps at the user and exits.
 * 
 * @author Natan Wojcik & redacted
 * @version 0.1
 * @since 2023 
 */
public class GyroBeeping implements Behavior {
 
	/**
	 * Empty constructor for GyroBeeping behavior.
	 * 
	 * @return none
	 */
	public GyroBeeping() { }
	
	/**
	 * Beeps at the user, displays robot is not aligned on the screen and then should exit the program.
	 * 
	 * @return none
	 */
	public void action() {
		Sound.beep();
		LCD.drawString("Robot is not aligned!", 0, 0);
		Delay.msDelay(2000);

		StateHandler.setState(States.EXIT);
		BehaviorData.setExititng(true);
		
		if (!SingleClient.clientConnected()) SingleClient.sendStatePacket(StateHandler.getState());
	}

	/**
	 * Empty surpress for GyroBeeping.
	 * 
	 * @return none
	 */
	public void suppress() {}
	
	/**
	 * Takes control if the condition of Marker.markerBeepGyro() is met for poor alignment.
	 * 
	 * @return boolean
	 */
	public boolean takeControl() {
		return Marker.markerBeepGyro();
	}
}