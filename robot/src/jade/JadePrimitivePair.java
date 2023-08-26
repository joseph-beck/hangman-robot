package jade;

/**
 * Stores a pair of primitives for Jade.
 * 
 * @author Joseph Beck
 * @version 0.1
 * @since   2023-02
 */
public class JadePrimitivePair<T, V> {
    
    /**
     * Stores the first variable.
     */
    private T first;
    /**
     * Stores the second variable.
     */
    private V second;

    /**
     * Constructor for the JadePrimitivePair that assigns both first and second to null.
     * 
     * @return none
     */
    public JadePrimitivePair() {
        this.first = null;
        this.second = null;
    }
    
    /**
     * Takes in to genericly typed parameters and assigns them to first and second.
     * 
     * @param T first
     * @param V second
     * @return none
     */
    public JadePrimitivePair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets the value in first.
     * 
     * @return T
     */
    public T getFirst() { 
        return this.first; 
    }

    /**
     * Sets the value of first.
     * 
     * @param T first
     * @return none
     */
    public void setFirst(T first) { 
        this.first = first; 
    }

    /**
     * Gets the value in second.
     * 
     * @return V
     */
    public V getSecond() { 
        return this.second; 
    }

    /**
     * Sets the value of second.
     * 
     * @param V second
     * @return none
     */
    public void setSecond(V second) { 
        this.second = second;
    }
}