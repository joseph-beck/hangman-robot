package jade;

/**
 * The JadeValidationException occurs when trying to validate a JadeObject.
 * This helps to better understand where and how the conversion went wrong.
 * 
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */
public class JadeValidationException extends JadeException {

    /**
     * Simple JadeValidationException where it is initiliazes its superclass with a generic message.
     * 
     * @return none
     */
    public JadeValidationException() {
        super("invalid jade validation occurred");
    }

    /**
     * Simple JadeValidationException where it is initiliazes its superclass with a generic message.
     * 
     * @param String error message
     * @return none
     */
    public JadeValidationException(String errorMessage) {
        super("invalid jade validation occurred" + errorMessage);
    }
}