package drawing.other;

/**
 * Interface for drawing a Symbol to abstarct the implementors.
 * 
 * @author Joseph Beck
 * @version 0.1
 * @since 2023 
 */
public abstract interface Symbol {
    
    /**
     * Stores the draw method.
     * 
     * @return none
     */
    public abstract void draw();

    /**
     * Stores the draw method and the amount of times it needs to be drawn.
     * 
     * @param int amount
     * @return none
     */
    public abstract void draw(int amount);
}
