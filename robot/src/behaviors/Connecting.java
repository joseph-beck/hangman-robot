package behaviors;

import client.SingleClient;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import robot.StateHandler;
import robot.States;

/**
 * Connects to the Java server through the SingleClient.
 * 
 * @author Joseph Beck
 * @version 0.1
 * @since 2023 
 */
public class Connecting implements Behavior {

    /**
     * Tries to initialize the client.
     * 
     * @return none
     */
    public Connecting() {
        SingleClient.initialize();
    }

    /**
     * Whilst the client is not connected tries to connect.
     * This can also throw an exception further up the call stack if the server does not exist.
     * 
     * @return none
     */
    @Override
    public void action() {
        while (!SingleClient.clientConnected()) {
            LCD.drawString("Connecting", 0, 4);
            SingleClient.initialize();
            LCD.clear();
        }

        if (SingleClient.clientConnected()) LCD.drawString("Connected", 0, 4);
        StateHandler.setState(States.BACK_UP);
    }

    /**
	 * Supress function does nothing here.
	 * 
	 * @return none
	 */    
    @Override
    public void suppress() {
        
    }

    /**
	 * Takes control when the state is equal to the CONNECTING state.
	 * 
	 * @return boolean
	 */
    @Override
    public boolean takeControl() {
        return StateHandler.getState() == States.CONNECTING;
    }
}