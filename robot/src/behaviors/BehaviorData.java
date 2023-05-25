package behaviors;

import drawing.Tile;

/**
 * A singleton to store data that needs to be used at runtime by multiple behaviors.
 * 
 * @author Joseph Beck
 * @version 0.1
 * @since 2023 
 */
public final class BehaviorData {

    /**
     * Stores the instance of BehaviorData.
     */
    private static BehaviorData instance;
    /**
     * Stores the users current guess.
     */
    private static char currentGuess;
    /**
     * Stores the word the game is being played with.
     */
    private static String wordToGuess;
    /**
     * Stores the tacho count of the motor.
     */
    private static int tachoCount;
    /**
     * Stores the permanent tacho count, this is the gap between the marker line and the robots starting position.
     */
    private static int permanentTachoCount;
    /**
     * Stores the boolean of whether or not the game has been won.
     */
    private static boolean gameWon;
    /**
     * Stores the boolean of whether or not the robot is exiting.
     */
    private static boolean exiting;
    /**
     * Stores the Tile array for the word and its tiles.
     */
    private static Tile[] tiles;

    /**
     * Initializes all of the variables of the singleton.
     * 
     * @return none
     */
    public BehaviorData() {
        currentGuess = ' ';
        wordToGuess = "";

        permanentTachoCount = 0;
        tachoCount = 0;

        gameWon = false;
        exiting = false;

        tiles = null;
    }

    /**
     * Gets an instance of this object and returns it, if it is null it creates an instance of this object.
     * Can also make static method calls to the object itself.
     * 
     * @return BehaviorData
     */
    public static BehaviorData getInstance() {
        if (instance == null) instance = new BehaviorData();

        return instance;
    }

    /**
     * Changes the value of currentGuess.
     * 
     * @param char guess
     * @return none
     */
    public static void setCurrentGuess(char guess) {
        currentGuess = guess;
    }

    /**
     * Returns the currentGuess.
     * 
     * @return char
     */
    public static char getCurrentGuess() {
        return currentGuess;
    }

    /**
     * Changes the current value of wordToGuess.
     * 
     * @param String word
     * @return none
     */
    public static void setWordToGuess(String word){
        wordToGuess = word;
    }

    /**
     * Returns the wordToGuess.
     * 
     * @return String
     */
    public static String getWordToGuess(){
        return wordToGuess;
    }

    /**
     * Changes the value of tachoCount.
     * 
     * @param int tacho
     * @return none
     */
    public static void setTachoCount(int tacho) {
        tachoCount = tacho;
    }

    /**
     * Returns the tacho count last put here.
     * 
     * @return int
     */
    public static int getTachoCount() {
        return tachoCount;
    }

    /**
     * Changes the value of the permanenTachoCount.
     * 
     * @param int tacho
     * @return char
     */
    public static void setPermanentTachoCount(int tacho){
        permanentTachoCount = tacho;
    }

    /**
     * Returns the permenantTachoCount.
     * 
     * @return int
     */
    public static int getPermanentTachoCount(){
        return permanentTachoCount; 
    }

    /**
     * Changes the value of gameWon.
     * 
     * @param boolean won
     * @return none
     */
    public static void setGameWon(boolean won) {
        gameWon = won;
    }

    /**
     * Returns the whether the game has been won.
     * 
     * @return boolean
     */
    public static boolean getGameWon() {
        return gameWon;
    }

    /**
     * Changes the value of exit.
     * 
     * @param boolean exit
     * @return none
     */
    public static void setExititng(boolean exit){
        exiting = exit;
    }

    /**
     * Returns whether or not the program is exiting.
     * 
     * @return boolean
     */
    public static boolean getExiting(){
        return exiting;
    }

    /**
     * Changes the value of tileArray
     * 
     * @param Tile[] tileArray
     * @return none
     */
    public static void setTiles(Tile[] tileArray) {
        tiles = tileArray;
    }

    /**
     * Returns an array of tiles pertaining to the word.
     * 
     * @return Tile[]
     */
    public static Tile[] getTiles() {
        return tiles;
    }    
}