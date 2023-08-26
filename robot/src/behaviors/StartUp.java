package behaviors;

import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import robot.*;
import drawing.*;
import drawing.other.symbols.UnderScore;
import util.ReadWord;

/**
 * Creates the board for the user to play the game of hangman on.
 * Also does other various start up activites.
 * 
 * @author redacted
 * @version 0.1
 * @since 2023 
 */
public class StartUp implements Behavior {

	/**
	 * Stores the hangman box distance.
	 */
	private static final int HANGMAN_BOX_DISTANCE = 775;
	/**
	 * Stores the word distance.
	 */
	private static final int WORD_DISTANCE = 375;
	/**
	 * Stores the box size.
	 */
	private static final int BOX_SIZE = 690;

	/**
	 * Stores the ReadWord object, used for reading in words from a file.
	 */
	private ReadWord readWord;
	/**
	 * Stores an instance of the UnderScoer object.
	 */
	private UnderScore underScore;
	/**
	 * Stores the word to be guessed.
	 */
	private String word;
	/**
	 * 	Stores an array of tiles for each characater of the word.
	 */
	private Tile[] tiles;
	
	/**
	 * Constructor for startup initializes objects and gets a word to be guessed.
	 * Also assigns things in behavior data.
	 * 
	 * @return none
	 */
	public StartUp() {
		this.underScore = new UnderScore();
		this.readWord = new ReadWord("words.txt");

		this.word = readWord.getRandomWord().toUpperCase();
		BehaviorData.setWordToGuess(this.word);
		
		tiles = new Tile[word.length()];
		makeTiles();
		BehaviorData.setTiles(this.tiles);
	}

	/**
	 * Makes an array of tiles that contains both their offsets and the character of it.
	 */
	private void makeTiles() {
		int distance = HANGMAN_BOX_DISTANCE;

		for (int i = 0; i < word.length(); i++) {
			tiles[i] = new Tile(word.charAt(i), distance);
			distance += WORD_DISTANCE;
		}
		LCD.drawInt(tiles.length, 0, 6);
	}

	/**
	 * Calculates the distance for drawing the underscores and where the hangman box will go.
	 * 
	 * @return none
	 */
	@Override
	public void action() {
		int fowardsDist = WORD_DISTANCE * word.length();
		
		Marker.markerHorizontal(fowardsDist + BOX_SIZE);
		underScore.draw(word.length());
		
		StateHandler.setState(States.CONNECTING);        
	}

	/**
	 * Empty surpress StartUp.
	 * 
	 * @return none
	 */
	@Override
	public void suppress() { }

	/**
     * Takes control when the state is equal to the START_UP state.
     * 
     * @return boolean
     */
	@Override	
	public boolean takeControl() {
		return StateHandler.getState() == States.START_UP;
	}
}