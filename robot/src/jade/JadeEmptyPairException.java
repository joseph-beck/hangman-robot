package jade;

/**
 * The JadeEmptyPairException is used in the case of a method being used on an empty JadePair.
 * This helps understand better what error has occured over using a generic Exception.
 *
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */
public class JadeEmptyPairException extends JadeException {

    /**
     * Simple JadeEmptyPairException where it is initiliazes its superclass with a generic message.
     * 
     * @return none
     */
    public JadeEmptyPairException() {
        super("empty jade pair exception");
    }

    /**
     * Simple JadeEmptyPairException where it is initiliazes its superclass with a specialised message.
     * 
     * @param String error message
     * @return none
     */
    public JadeEmptyPairException(String errorMessage) {
        super("empty jade pair exception: " + errorMessage);
    }
}