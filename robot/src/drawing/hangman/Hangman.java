package drawing.hangman;

import drawing.hangman.stages.Arms;
import drawing.hangman.stages.Body;
import drawing.hangman.stages.BottomBar;
import drawing.hangman.stages.Head;
import drawing.hangman.stages.LeftBar;
import drawing.hangman.stages.Legs;
import drawing.hangman.stages.TopBar;

/**
 * This manages the stages of the hangman drawings by creating an
 * array of stages which are assigned to an object.
 * 
 * @author Joseph Beck
 * @version 0.1
 * @since 2023 
 */
public class Hangman {
    /**
     * Stores an array of Stage objects
     */
    private Stage[] stageArray;

    /**
     * Constructor of the HangMan object.
     * 
     * @return none
     */
    public Hangman() {
        makeStages();
    }

    /**
     * Assigns a integer to a Stage object.
     * 
     * @return none
     */
    private void makeStages() {
        stageArray = new Stage[7];

        stageArray[0] = new BottomBar();
        stageArray[1] = new LeftBar();
        stageArray[2] = new TopBar();
        stageArray[3] = new Head();
        stageArray[4] = new Body();
        stageArray[5] = new Arms();
        stageArray[6] = new Legs();
    }
    /**
     * This returns the stageArray.
     * 
     * @return Stage[]
     */
    public Stage[] getStageArray() {
        return this.stageArray;
    }
}