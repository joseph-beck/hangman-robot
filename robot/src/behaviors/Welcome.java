package behaviors;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import robot.StateHandler;
import robot.States;

/**
 * Shows a welcome screen for the user.
 * 
 * @author redacted
 * @version 0.1
 * @since 2023 
 */
public class Welcome implements Behavior{

    private String version;

    public Welcome(String version) {
        this.version = version;
    }
  
    /**
     * On the LCD screen creates a welcome screen and waits until the button is pressed and released.
     * Once this happens clears the screen and sets the state to BACK_UP.
     * 
     * @return none
     */
    @Override
    public void action() {
        LCD.drawString("Welcome to Hangman by:",0 , 1);
        LCD.drawString("Guleed Abdule", 0, 2);
        LCD.drawString("Ayan Mohamed", 0, 3);
        LCD.drawString("Natan Wojcik", 0, 4);
        LCD.drawString("Joseph Beck", 0, 5);
        LCD.drawString("Version: " + version, 0, 6);

        Button.ENTER.waitForPressAndRelease();

        LCD.clear();
        
        StateHandler.setState(States.BACK_UP);
    }

    /**
     * Empty supress for Welcome.
     * 
     * @return none
     */
    @Override
    public void suppress() { }

    /**
     * Takes control when the state is equal to the WELCOME state.
     * 
     * @return boolean
     */
    @Override
    public boolean takeControl() {
      return StateHandler.getState() == States.WELCOME;
    }
}