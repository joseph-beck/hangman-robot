package jade;

/**
 * The JadeConversionException occurs when trying to convert a JadeObect to another object.
 * This helps to better understand where and how the conversion went wrong.
 * 
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */
public class JadeConversionException extends JadeException {

    /**
     * Simple JadeConversionException where it is initiliazes its superclass with a generic message.
     * 
     * @return none
     */
    public JadeConversionException() {
        super("illegal conversion exception");
    }

    /**
     * Simple JadeConversionException where it is initiliazes its superclass with a specialised message.
     * 
     * @param String error message
     * @return none
     */
    public JadeConversionException(String errorMessage) {
        super("illegal conversion exception: " + errorMessage);
    }
}