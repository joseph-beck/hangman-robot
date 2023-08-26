package client;

/**
 * The PacketConversionException is used when trying to convert an object of type Packet to something else.
 * It has its own unique exception in order to provide greater clarity when errors occur.
 * 
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */
public class PacketConversionException extends Exception {

    /**
     * Simple PacketConversionException where it is initiliazes its superclass with a generic message.
     * 
     * @return none
     */
    public PacketConversionException() {
        super("packet conversion exception");
    }

    /**
     * Simple PacketConversionException where it is initiliazes its superclass with a specialised message.
     * 
     * @param String error message
     * @return none
     */
    public PacketConversionException(String errorMessage) {
        super("packet conversion exception: " + errorMessage);
    }
}