package behaviors;

import client.SingleClient;
import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;
import robot.StateHandler;
import robot.States;

/**
 * When a button is pressed causes the robot to emergency stop and exit the program.
 * 
 * @author Natan Wojcik & redacted
 * @version 0.1
 * @since 2023 
 */
public class EmergencyStop implements Behavior {

	/**
	 * Empty constructor for EmergencyStop behavior.
	 * 
	 * @return none
	 */
	public EmergencyStop() { }

	/**
	 * Sets the state for exit and sends a packet to the client if its connected saying it is exiting.
	 * The exit state does not return true any behavior resulting in it terminating the arbitrator.
	 * 
	 * @return none
	 */
	@Override
	public void action() {
		StateHandler.setState(States.EXIT);
		BehaviorData.setExititng(true);
		if (!SingleClient.clientConnected()) SingleClient.sendStatePacket(StateHandler.getState());
	}

	/**
	 * Supress function does nothing here.
	 * 
	 * @return none
	 */
	@Override
	public void suppress() { }

	/**
	 * Take control returns true when the down button is being pressed down.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean takeControl() {
		return (Button.DOWN.isDown());
	}
}