package robot;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import behaviors.*;

/**
 * Starts the robot using the run method, which is called in main.
 *
 * @author Joseph Beck & Natan Wojcik
 * @version 0.1
 * @since 2023
 */
public class Robot {

	/**
	 * Stores the version of the robot.
	 */
	private String version;

	/**
	 * Constructor takes in the version of the robot and then initialzies the value of version.
	 *
	 * @param String version
	 * @return none
	 */
	public Robot(String version) {
		this.version = version;
	}

	/**
	 * Runs the robot and creates all of the behaviors for it.
	 * These behaviors are added to the arbitrator and then it is ran.
	 *
	 * @return none
	 */
	public void run() {
		StateHandler.getInstance();
		BehaviorData.getInstance();

		// Start screen.
		Behavior welcome = new Welcome(version);

		// Stopping behaviors.
		Behavior emergencyStop = new EmergencyStop();
		Behavior battery = new BatteryLevel();
		Behavior gyro = new GyroBeeping();

		// Misc and initializing behaviors.
		Behavior startUp = new StartUp();
    	Behavior backUp = new BackUp();
		Behavior connect = new Connecting();

		// Game behaviors.
		Behavior drawing = new Drawing();
		Behavior guess = new Guessing();

		// FInishing behaviors.
		Behavior finished = new Finished();
		Behavior exit = new Exit();

		Arbitrator arbitrator = new Arbitrator(new Behavior[] { 
				exit, backUp, startUp, connect, guess, drawing, finished, welcome, battery, emergencyStop, gyro
			},
			true
		);

		arbitrator.go();

		LCD.clear();

		Button.ENTER.waitForPressAndRelease();
	}
	/**
	 * Retruns the value of version.
	 *
	 * @return String
	 */
	public String getVersion() {
		return this.version;
	}
}