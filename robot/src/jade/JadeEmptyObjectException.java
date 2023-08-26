package jade;

/**
 * The JadeEmptyObjectException is used in the case of a method being used on an empty JadeObject.
 * This helps understand better what error has occured over using a generic Exception.
 *
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */
public class JadeEmptyObjectException extends JadeException {

    /**
     * Simple JadeEmptyObjectException where it is initiliazes its superclass with a generic message.
     * 
     * @return none
     */
    public JadeEmptyObjectException() {
        super("empty jade object exception");
    }

    /**
     * Simple JadeEmptyObjectException where it is initiliazes its superclass with a specialised message.
     * 
     * @param String error message
     * @return none
     */
    public JadeEmptyObjectException(String errorMessage) {
        super("empty jade object exception: " + errorMessage);
    }
}