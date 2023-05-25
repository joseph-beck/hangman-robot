package drawing.letters;

/**
 * Abstract interface for drawing a letter, this is implemented by all letter Objects.
 * 
 * @author Joseph Beck
 * @version 0.1
 * @since 2023
 */
public abstract interface Letter {
    
    /**
     * Draws the letter.
     * 
     * @return none
     */
    public abstract void draw();


    /**
     * Draws the letter with an offset.
     * 
     * @param int offset
     * @return none
     */
    public abstract void draw(int offset);

    /**
     * Does offset behavior.
     * 
     * @param offset
     * @return none
     */
    public abstract void offset(int offset);

    /**
     * Gets the character of the object.
     * 
     * @return none
     */
    public abstract char getChar();
}