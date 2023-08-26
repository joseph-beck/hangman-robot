package behaviors;

import client.SingleClient;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import robot.*;

/**
 * Shows a finished screen once the game is over.
 * 
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023 
 */

public class Finished implements Behavior {

    /**
     * Stores the word that the user was earlier guessing.
     */
    private String word;
    /**
     * Stores whether or not the word was successsfullyu guessed.
     */
    private boolean wordGuessed;


    /**
     * Constructor of Finished, assigns the the word to guess.
     * 
     * @return none
     */
    public Finished() {
          this.word = BehaviorData.getWordToGuess();
    }
  
    /**
     * Actions sends a state packet to the server so that the client knows the game is over.
     * Also gets whether the was won and its output screen varies on whether or not the user won.
     * 
     * @return none
     */
    @Override
    public void action() {
        SingleClient.sendStatePacket(StateHandler.getState());
        wordGuessed = BehaviorData.getGameWon();
        
        if(wordGuessed) { // Shows the winning screen
            LCD.clear();
            LCD.drawString("You have won!", 0, 4);
            Sound.playTone(1000, 50);
            Button.ENTER.waitForPressAndRelease();
            System.exit(1);
        } else { // Shows the losing screen.
            LCD.clear();
            LCD.drawString("You have lost", 0, 4);
            LCD.drawString("The word was", 0, 5);
            LCD.drawString(word, 0, 6);
            Sound.playTone(100, 50);
            Button.ENTER.waitForPressAndRelease();
            System.exit(1);
        }
    }

    /**
     * Empty surpess for Finished.
     * 
     * @return none
     */
    @Override
    public void suppress() { }

    /**
     * Takes control when the state is equal to the FINISHED state.
     * 
     * @return boolean
     */
    @Override
    public boolean takeControl() {
        return StateHandler.getState() == States.FINISHED;
    }    
}