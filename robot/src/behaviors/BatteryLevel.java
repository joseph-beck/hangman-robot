package behaviors;

import client.SingleClient;
import lejos.hardware.Battery;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import robot.StateHandler;
import robot.States;

/**
 * Checks whether the battery level is low, if it is exits the program.
 * 
 * @author redacted & Natan Wojcik
 * @version 0.1
 * @since 2023 
 */
public class BatteryLevel implements Behavior {
	
	/**
	 * Null battery level constructor
	 * 
	 * @return none
	 */
	public BatteryLevel() { }

	/**
	 * Action displays on the screen that the battery is low waits and then exits the program.
	 * 
	 * @return none
	 */
	@Override
	public void action() {
		LCD.drawString("Battery Level Low", 0, 5);
		Sound.beep();
		Delay.msDelay(3000);
		LCD.clear();

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
	 * Take control returns true when the battery level is below 7 volts.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean takeControl() {
		return (Battery.getVoltage() < 7.0f);
	}
}