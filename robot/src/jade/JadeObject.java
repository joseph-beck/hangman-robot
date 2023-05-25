package jade;

import client.Packet;

/**
 * The JadeObject class implements the object JadeObject which stores an array of JadePairs.
 * JadeObjects are able to be serialized and deserialized to strings by the respective tools.
 * JadeObjects can also be converted to Packets in order to send data in a formatted way over a TCP Server.
 * 
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */
public class JadeObject {

    /**
     * Stores an array of JadePairs as args.
     */
    private JadePair[] args;

    /**
     * Constructor of the Jade Object, initialises args.
     * 
     * @param JadePair[] arguments
     * @return none
     */
    public JadeObject(JadePair[] args) {
        this.args = args;
    }

    /**
     * This method converts the current jade object to a new object of type Packet.
     * 
     * @return Packet
     * @exception JadeConversionException
     */
    public Packet toPacket() {
        try {
            if (validateArgs()) {
                return makePacket();
            } else {
                throw new JadeConversionException("args length = " + args.length);
            }
        } catch (JadeConversionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method, makePacket, makes a new object of type Packet from the args (JadePairs) of this object.
     * 
     * @return Packet
     */
    private Packet makePacket() {
        return new Packet(  args[0].getSecond(), 
                            args[1].getSecond(),
                            args[2].getSecond(),
                            args[3].getSecond(),
                            args[4].getSecond());
    }

    /**
     * This method, validateArgs, is used to check whether the current JadeObject is able to be converted to type Packet.
     * 
     * @return boolean
     */
    private boolean validateArgs() {
        try {
            if (args.length == 5) {
                return (
                    args[0].getFirst().equals("sender") && 
                    args[1].getFirst().equals("reciever") &&
                    args[2].getFirst().equals("type") &&
                    args[3].getFirst().equals("data") &&
                    args[4].getFirst().equals("checkSum"));
            } 
            throw new JadeValidationException("args length of " + args.length + " does not satisfy the requirements");
        } catch (JadeValidationException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        } 
    }   

    /**
     * This method check whether the args of the Jade Object is empty, true for empty false for not.
     * 
     * @return boolean
     */
    public boolean isEmpty() { 
        return args.length == 0; 
    }

    /**
     * getArgs returns the JadePair array that stores the arguments of the Jade object.
     * 
     * @return JadePair[] args
     */
    public JadePair[] getArgs() { 
        return this.args; 
    }

    /**
     * This method returns a string representation of the Jade Object.
     * 
     * @return String
     */
    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < args.length; i++) {
            output += (i < args.length-1) ? args[i] + "\n" : args[i]; 
        }

        return output;
    }
}