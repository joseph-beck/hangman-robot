package drawing;

/**
 * Store a tile which makes drawing words easier
 * 
 * @author Joseph Beck
 * @version 0.1
 * @since 2023 
 */
public class Tile {
    
    /**
     * Stores the character.
     */
    private char character;
    /**
     * Stores the letters offset.
     */
    private int offset;

    /**
     * Constructor takes in two parameters and initlaizes character and offset.
     * 
     * @param char character
     * @param int offset
     * @return none
     */
    public Tile(char character, int offset) {
        this.character = character;
        this.offset = offset;
    }

    /**
     * Returns the character.
     * 
     * @return char
     */
    public char getCharacter() {
        return this.character;
    }

    /**
     * Sets the character
     * 
     * @param char character
     * @return none
     */
    public void setCharacter(char character) {
        this.character = character;
    }

    /**
     * Returns the offset
     * 
     * @return int
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Sets the offset
     * 
     * @param int offset
     * @return none
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }
}