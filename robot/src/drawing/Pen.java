package drawing;

import behaviors.BehaviorData;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import robot.Motors;
import robot.Sensors;

/**
 * This is the object which manages the functionality of the pen. 
 * It contains abstractions which make it easier to draw out what is needed.
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023 
 */
public class Pen {
    /**
	 * Stores the smallMotors Rotation amount for up and down.
	 */
    private static final int SMALL_MOTOR_ROTATE = 90;
	/**
	 * Stores the medium motor speeds.
	 */
	private static final int MEDIUM_MOTOR_SPEED = 200;
	/**
	 * Stores the pen delay.
	 */
	private static final int PEN_DElAY = 50;
	/**
	 * Stores the small motor speeds.
	 */
	private static final int SMALL_MOTOR_SPEED = 250;
	/**
	 * Stores the tachoCount threshold.
	 */
	private static final int TACHO_HOLD = -100;
	/**
	 * Stores the gyroSensor's threshold.
	 */
	private static final float GYRO_THRESHOLD = 5.0F;
	/**
	 * Stores the colourSensor's threshold.
	 */
	private static final float COLOUR_THRESHOLD = 0.025F;

	/**
	 * Stores the boolean for checking if the pen is Up.
	 */
    private boolean isPenUp; 
	/**
	 * Stores the SampleProvider object for touchSensor.
	 */
    private SampleProvider touchProvider;
	/**
	 * Stores the SampleProvider object for gyroSensor.
	 */
    private SampleProvider gyroProvider;
	/**
	 * Stores the Motor obect for the fowards motor.
	 */
	private BaseRegulatedMotor forwards;
	/**
	 * Stores the Motor obect for the horizontal motor.
	 */
	private BaseRegulatedMotor horizontal;
	/**
	 * Stores the Motor obect for the small motor.
	 */
	private BaseRegulatedMotor small;
	/**
	 * Stores the Sesnor obect for the touchSenor.
	 */
	private EV3TouchSensor touchSensor;
	/**
	 * Stores the Sesnor obect for the gyroSensor.
	 */
	private EV3GyroSensor gyroSensor;
	
	/**
	 * Consturtor which opens the Motor and Sensor singletons aswell as setting the speeds of motors and intialising the boolean 'isPenUp'.
	 * 
	 *  @return none
	 */
    public Pen() {
    	open();

    	forwards.setSpeed(MEDIUM_MOTOR_SPEED);
  		horizontal.setSpeed(MEDIUM_MOTOR_SPEED);
  		small.setSpeed(SMALL_MOTOR_SPEED );
  		
  		this.isPenUp = true;
    }

	/**
	 * Method which gets the instances' of the Motor and Sensor singletons.
	 * 
	 * @return none
	 */
	public void open() {
		Motors.getInstance();
		this.forwards = Motors.getForwards();
		this.horizontal = Motors.getHorizontal();
		this.small = Motors.getSmall();
    	
    	Sensors.getInstance();
    	this.touchSensor = Sensors.getTouchSensor();
		this.gyroSensor = Sensors.getGyroSensor();
		
		this.touchProvider =Sensors.getTouchProvider();
		this.gyroProvider =Sensors.getGyroProvider();
	}

	/**
	 * Returns the tachoCount of the forwards motor.
	 * 
	 * @return int
	 */
	public int tachoCount() {
		return forwards.getTachoCount();
	}

	/**
	 * Closes all motors and sensors
	 * 
	 * @return none
	 */
    public void close() {
    	forwards.close();
    	horizontal.close();
    	small.close();
    	touchSensor.close();
    	gyroSensor.close();
    }

    /**
	 * This uses the touchSensor to calibrate the pen on its Vertical axis as well putting the pen back up.
	 * This is done by moving the motor until it touchs the sensor.
	 * 
	 * @return none
	 */
    public void calibrate() {
    	float[] samplesTouch = new float[1];
	    boolean seen = false;
	    if(!isPenUp) {
	    	penUp();
	    }
	    while(seen == false) {
	    	touchProvider.fetchSample(samplesTouch, 0);
	    	horizontal.forward();
	    	if(samplesTouch[0] == 1) {
	    		horizontal.stop(true);
	    		seen = true;
	    	}
	    }
    }

 	/**
	 * This puts the pen up by rotating it positvely .
	 * 
	 * @return none
	 */
    public void penUp() {
       Delay.msDelay(PEN_DElAY);
       small.rotate(SMALL_MOTOR_ROTATE);
       isPenUp = true;
    }

    /**
	 * This puts the pen down by rotating it negitvely.
	 * 
	 * @return none
	 */
    public void penDown() {
       small.rotate(-SMALL_MOTOR_ROTATE);
       Delay.msDelay(PEN_DElAY);
       isPenUp = false;
    }

	/**
	 * This stops the motor from moving forwards.
	 * 
	 * @return none
	 */
	public void penStop() {
		forwards.stop();
	}


	/**
	 * This uses a horizontal and forwards input inorder to draw from Left to Right diagonally
	 * by synching the motors and moving them at the same time.
	 * 
	 * @param int horizonalRotate
	 * @param int fowardsRotate
	 * @return none
	 */
    public void penDiagonalLR(int horizontalRotate, int fowardsRotate) {
    	forwards.synchronizeWith(new BaseRegulatedMotor[] {horizontal});
    	forwards.startSynchronization(); 
    	forwards.rotate(fowardsRotate,true);
    	horizontal.rotate(horizontalRotate,true);
    	forwards.endSynchronization(); 
    	forwards.waitComplete();
    	horizontal.waitComplete();
    }

    /**
	 * This uses a horizontal and forwards input inorder to draw from Right to Left diagonally
	 * by synching the motors and moving them at the same time.
	 * 
	 * @param int horizonalRotate
	 * @param int fowardsRotate
	 * @return none
	 */
    public void penDiagonalRL(int horizontalRotate, int fowardsRotate) {
    	forwards.synchronizeWith(new BaseRegulatedMotor[] {horizontal});
    	forwards.startSynchronization(); 
    	forwards.rotate(-fowardsRotate,true);
    	horizontal.rotate(horizontalRotate,true);
    	forwards.endSynchronization(); 
    	forwards.waitComplete();
    	horizontal.waitComplete();
    }

	/**
	 * This moves the pen vertically by the horizontal rate.
	 * 
	 * @param int horizonalRotate
	 * @return none
	 */
    public void penVertical(int horizontalRotate) {
    	 horizontal.rotate(horizontalRotate);
    }

	/**
	 * This moves the robot backwards.
	 * 
	 * @return none
	 */
    public void penBackwards() {
		forwards.backward();
    }

	/**
	 * This moves the pen Horizontally by the forwards rate.
	 * 
	 * @param int fowardsRotate
	 * @return none
	 */
    public void penHorizontal(int fowardsRotate) {
         forwards.rotate(fowardsRotate);
    }

	/**
	 * This method causes the robot to move backwards up until the colourSensor detects a black line past a certain TachoCount threshold.
	 * 
	 * @return none
	 */
    public void backUpPen() {
		Sensors.getInstance();
		SampleProvider colourProvider = Sensors.getColourProvider();
		float[] samplesColour = new float[1];
	    boolean seen = false;
	    BehaviorData.setTachoCount(0);
        int tacho = 0;
		while(!seen ) {
			tacho = tachoCount();
            penBackwards();
        	colourProvider.fetchSample(samplesColour, 0);
          	if(samplesColour[0] < COLOUR_THRESHOLD && tacho < TACHO_HOLD) {
				seen = true;
				penStop();
			}
		}
	}
  	/**
	 * This checks if the angle of gyroSensor past a certain threshold.
	 * 
	 * @return boolean
	 */
    public boolean beepGyro() {
    	float[] samplesGyro = new float[1];
    	gyroProvider.fetchSample(samplesGyro, 0);
    	
    	if(samplesGyro[0] > GYRO_THRESHOLD) {
    		return true;
    	}
    	return false;
   	}
}