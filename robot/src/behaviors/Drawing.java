package behaviors;

import java.util.HashMap;

import client.SingleClient;
import drawing.Marker;
import drawing.Tile;
import drawing.hangman.Hangman;
import drawing.hangman.Stage;
import drawing.letters.Alphabet;
import drawing.letters.Letter;
import lejos.robotics.subsumption.Behavior;
import robot.StateHandler;
import robot.States;

/**
 * Draws either a piece of the hangman or (a) character(s) depending on the given guess.
 * 
 * @author Natan Wojcik & Joseph Beck
 * @version 0.1
 * @since 2023 
 */
public class Drawing implements Behavior {
    
    /**
     * Stores the amount of failed guesses.
     */
    private int failedGuesses;
    /**
     * Stores the amount of correct guesses.
     */
    private int correctGuesses;
    /**
     * Stores the boolean value of whether the word has been guessed.
     */
    private boolean wordGuessed;
    /**
     * Stores the word that is currently being guessed.
     */
    private String word;
    /**
     * Stores an array of stages, which are the stages of the hangman.
     */
    private Stage[] hangmanStages;
    /**
     * Stores an array of Tile objects that pertain to the letters of the word.
     */
    private Tile[] tiles;
    /**
     * Stores a character and its corresponding letter so that it can be drawn.
     */
    private HashMap<Character, Letter> alphabet;

    /**
     * The constructor of drawing initializes all variables that require an initial value.
     * 
     * @return none
     */
    public Drawing() {
        BehaviorData.getInstance();

        this.failedGuesses = 0;
        this.wordGuessed = false;
        this.word = BehaviorData.getWordToGuess();
        this.tiles = BehaviorData.getTiles();

        Alphabet alphabetMaker = new Alphabet();
        this.alphabet = alphabetMaker.getAlphabet();

        Hangman stageMaker = new Hangman();
        this.hangmanStages = stageMaker.getStageArray();
    }

    /**
     * Sends a state packet to the server and then gets the current guess.
     * With the current guess it then draws either a piece of hangman or the character based on the correctness of it.
     * 
     * @return none
     */
    @Override
    public void action() {
        SingleClient.sendStatePacket(StateHandler.getState());
        if (this.tiles == null) tiles = BehaviorData.getTiles();
        
        boolean correctCharacterGuess = false;
        char guess = BehaviorData.getCurrentGuess();
        if (guess == ' ') return;
        
        BehaviorData.setCurrentGuess(' ');

        for (Tile tile : tiles) {
            if (tile.getCharacter() == guess) { // Draws every character that is correct for the guess.
                correctCharacterGuess = true;
                
                Letter letter = alphabet.get(guess);
                letter.draw(tile.getOffset());
                
                Marker.markerBackUp();
                correctGuesses++;
            }
        }

        if(correctGuesses == word.length()) { // Checks if game has been won.
            wordGuessed = true;
            BehaviorData.setGameWon(true);
        }

        if (!correctCharacterGuess) { // If wrong guess then draws a piece of hangman.
            hangmanStages[failedGuesses].draw();
            failedGuesses++;
        }

        SingleClient.setLatestPacket(null);
        StateHandler.setState((failedGuesses == 7 || wordGuessed) ? States.FINISHED : States.BACK_UP);
    }

    /**
	 * Supress function does nothing here.
	 * 
	 * @return none
	 */    
    @Override
    public void suppress() { }

    /**
	 * Takes control when the state is equal to the DRAWING state.
	 * 
	 * @return boolean
	 */
    @Override
    public boolean takeControl() {
        return StateHandler.getState() == States.DRAWING;
    }
}