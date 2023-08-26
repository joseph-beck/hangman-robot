package robot;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * Stores the different motors uesd by the robot.
 *
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023
 */
public final class Motors {

	/**
	 * Stores the instance of this Motor object
	 */
	private static Motors instance;
	/**
	 * Stores ther forwards motor.
	 */
	private static BaseRegulatedMotor forwards;
	/**
	 * Stores the horizontal motor.
	 */
	private static BaseRegulatedMotor horizontal;
	/**
	 * 	Stores the small motor.
	 */
	private static BaseRegulatedMotor small;

	/**
	 * Initializes the different motors of the robot.
	 *
	 * @return none
	 */
	private Motors() {
		forwards = new EV3LargeRegulatedMotor(MotorPort.A);
		horizontal = new EV3LargeRegulatedMotor(MotorPort.B);
		small =  new EV3MediumRegulatedMotor(MotorPort.C);
	}

	/**
	 * Gets an instance of Motors and returns it.
	 * If it is null then an instace of it is created.
	 *
	 * @return Motors
	 */
	public static Motors getInstance() {
        if (instance == null) instance = new Motors();

        return instance;
    }

	/**
	 * Returns the forwards motor.
	 * If it is null creates an instance of it.
	 *
	 * @return BaseRegulatedMotor
	 */
	public static BaseRegulatedMotor getForwards() {
		if (forwards == null) forwards = new EV3LargeRegulatedMotor(MotorPort.A);

		return forwards;
	}

	/**
	 * Retrusns the horizontal motor.
	 * If it is null creates an instance of it.
	 *
	 * @return BaseRegulatedMotor
	 */
	public static BaseRegulatedMotor getHorizontal() {
		if (horizontal == null) horizontal = new EV3LargeRegulatedMotor(MotorPort.B);

		return horizontal;
	}

	/**
	 * Returns the small motor.
	 * If it is null creates an instance of it.
	 *
	 * @return BaseRegulatedMotor
	 */
	public static BaseRegulatedMotor getSmall() {
		if (small == null) small = new EV3LargeRegulatedMotor(MotorPort.C);

		return small;
	}
}