package drawing.hangman;

/**
 * This is the interface for Stage obejcts.
 * 
 * @author Joseph Beck
 * @version 0.1
 * @since 2023 
 */
public abstract interface Stage {
    
    /**
     * Defines an abstarct method for drawing.
     * 
     * @return none
     */
    public abstract void draw();

    /**
     * Defines an abstract method for getting stage number.
     * 
     * @return none
     */
    public abstract int getStageNumber();
}