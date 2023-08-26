package jade;

/**
 * The JadeException is a generic exception that inherits from type exception.
 * The Jade Exceptions inherit from this superclass and throw more specific errors.
 * 
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */
public class JadeException extends Exception {
    
    /**
     * The constructor of the base Jade Exception takes in a parameter of an error message and initialises the superclass Exception.
     * 
     * @param String error message
     * @return none
     */
    public JadeException(String errorMessage) {
        super(errorMessage);
    }
}