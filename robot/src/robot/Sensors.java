package robot;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

/**
 * Stores the sensors used by the robot.
 *
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023
 */
public final class Sensors {

	/**
	 * Stores the instance of this Sensors class.
	 */
	private static Sensors instance;
	/**
	 * Stores the touch sensor.
	 */
	private static EV3TouchSensor touchSensor;
	/**
	 * Stores the colour sensor.
	 */
	private static EV3ColorSensor colourSensor;
	/**
	 * Stores the gyro sensor.
	 */
	private static EV3GyroSensor gyroSensor;
	/**
	 * Stores the touch provider.
	 */
	private static SampleProvider touchProvider;
	/**
	 * Stores the gyro provider.
	 */
	private static SampleProvider gyroProvider;
	/**
	 * Stores the colour provider.
	 */
	private static SampleProvider colourProvider;

	/**
	 * The constructor of Sensors opens up all of the sensors and get the sample providers.
	 * 
	 * @return none
	 */
	private Sensors() {
		touchSensor = new EV3TouchSensor(SensorPort.S1);
		colourSensor = new EV3ColorSensor(SensorPort.S2);
		gyroSensor = new EV3GyroSensor(SensorPort.S4);
		
    	touchProvider  = touchSensor.getTouchMode();
    	colourProvider = colourSensor.getRedMode();
		gyroProvider = gyroSensor.getAngleMode();
	}
	
	/**
	 * Gets an instance of Sensors and returns it.
	 * If it is null then an instace of it is created.
	 * 
	 * @return Sensors
	 */
	public static Sensors getInstance() {
        if (instance == null) instance = new Sensors();
        
        return instance;
    }
	
	/**
	 * Returns the touch sensor.
	 * If it is null then an instace of it is created.
	 * 
	 * @return EV3TouchSensor.
	 */
	public static EV3TouchSensor getTouchSensor() {
		if (touchSensor == null) touchSensor= new EV3TouchSensor(SensorPort.S1);
		
		return touchSensor;
	}

	/**
	 * Returns the colour sensor.
	 * If it is null then an instace of it is created.
	 * 
	 * @return EV3TouchSensor.
	 */
	public static EV3ColorSensor getColourSensor() {
		if (colourSensor == null) colourSensor= new EV3ColorSensor(SensorPort.S2);

		return colourSensor;
	}

	/**
	 * Returns the gyro sensor.
	 * If it is null then an instace of it is created.
	 * 
	 * @return EV3TouchSensor.
	 */
	public static EV3GyroSensor getGyroSensor() {
		if (gyroSensor == null) gyroSensor = new EV3GyroSensor(SensorPort.S4);
		
		return gyroSensor;
	}
	
	/**
	 * Retruns the touch provider.
	 * If it is null then an instace of it is created.
	 * 
	 * @return SampleProvider
	 */
	public static SampleProvider getTouchProvider() {
		if (touchProvider== null) touchProvider= touchSensor.getTouchMode();
		
		return touchProvider;
	}

	/**
	 * Retruns the colour provider.
	 * If it is null then an instace of it is created.
	 * 
	 * @return SampleProvider
	 */
	public static SampleProvider getColourProvider() {
		if (colourProvider== null) colourProvider= colourSensor.getRedMode();
		
		return colourProvider;
	}

	/**
	 * Retruns the gyro provider.
	 * If it is null then an instace of it is created.
	 * 
	 * @return SampleProvider
	 */
	public static SampleProvider getGyroProvider() {
		if (gyroProvider== null) gyroProvider = gyroSensor.getAngleMode();
		
		return gyroProvider;
	}
}