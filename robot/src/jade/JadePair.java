package jade;

/**
 *  The JadePair acts like a tuple, it stores both a String value of first and second.
 *  This normally acts like a key pair with the first being the key and the second being the data it pertains to.
 * 
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */
public class JadePair {

    /**
     * String that stores the first variable.
     */
    private String first;
    /**
     * String that stores the second variable.
     */
    private String second;

    /**
     * Takes in two string parameters and initialises the object.
     * 
     * @param String first
     * @param String second
     * @return none
     */
    public JadePair(String first, String second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Takes in no parameters and initialises a null instance of JadePair.
     * 
     * @return none
     */
    public JadePair() {
        this.first = null;
        this.second = null;
    }

    /**
     * Takes in  two string parameters and initialises the object.
     * 
     * @param String first
     * @param String second
     * @return none
     */
    public void replace(JadePair pair) {
        this.first = pair.getFirst();
        this.second = pair.getSecond();
    }

    /**
     * Returns the value of the first variable.
     * 
     * @return String
     */
    public String getFirst() {
        return this.first;
    }

    /**
     * Sets the value of the first variable.
     * 
     * @param String first
     * @return none
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * Returns the value of the second variable.
     * 
     * @return String
     */
    public String getSecond() {
        return this.second;
    }

    /**
     * Sets the value of the second variable.
     * 
     * @param String second
     * @return none
     */
    public void setSecond(String second) {
        this.second = second;
    }

    /**
     * Returns a string representation of this Jade Pair object.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return first + " : " + second;
    }
}