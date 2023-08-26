package jade;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The JadeSerializer is used to convert an existing JadeObject to either a string or file.
 * As Jade does not care about new lines their is a pretty printing mode to improve the readability of the JadeObject.
 * <p>
 * Structure of an example Jade string:
 * <p>
 * <i>variableOne: 'valueOne';variableTwo: 'valueTwo';</i>
 * <p>
 * <i>:</i> -> used to split the variable and its value
 * <p>
 * <i>;</i> -> to denote the end of a varialbe
 * <p>
 * <i>'</i> -> surrounds a value of a variable
 *
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */
public class JadeSerializer {

    /**
     * Stores the boolean value of prettyPrinting.
     */
    private boolean prettyPrinting;

    /**
     * Constructor of the JadeSerializer, it initializes the value of pretty printing to false.
     * 
     * @return none
     */
    public JadeSerializer() {
        this.prettyPrinting = false;
    }

    /**
     * This method, serializeObjectToFile, writes a JadeObject to a given directory.
     * Depending on whether pretty printing is enabled or not the structure of the file may look different.
     * The changed structure does not affect the functionality of any of the JadeSystems.
     * 
     * @param JadeObject jade object
     * @param String directory
     * @return none
     */
    public void serializeObjectToFile(JadeObject obj, String dir) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dir))) {
            if (obj.isEmpty()) throw new JadeEmptyObjectException("object: " + obj.toString());
            
            JadePair[] objArgs = obj.getArgs();
            String outputString = makeOutputString(objArgs);

            writer.write(outputString);
        } catch (IOException | JadeException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method, serializeObjectToString, creates a string from a given JadeObject.
     * The structure of the string may also vary depending on pretty printing but not affect its functionality.
     * 
     * @param JadeObject jade object
     * @return String
     */
    public String serializeObjectToString(JadeObject obj) {
        if (obj.isEmpty()) {
            try {
                throw new JadeEmptyObjectException("object: " + obj.toString());
            } catch (JadeException e) {
                e.printStackTrace();
            }
        }

        JadePair[] objArgs = obj.getArgs();
        return makeOutputString(objArgs);
    }

    /**
     * This method, makeOutputString, takes in a JadePair array and creates the string based on each JadePair.
     * This follows the same convention as all JadeObject creation.
     * 
     * @param JadePair[] object arguments
     * @return String
     */
    private String makeOutputString(JadePair[] objArgs) {
        String output = "";

        for (int i = 0; i < objArgs.length; i++) {
            output += String.format(
                "%s : '%s';%s", 
                objArgs[i].getFirst(),
                objArgs[i].getSecond(),
                (prettyPrinting && i != objArgs.length-1) ? "\n" : "" 
            );
        }

        return output;
    }
    /**
     * Sets the value of prettyPrinting.
     * 
     * @param boolean pretty printing mode
     * @return none
     */
    public void setPrettyPrinting(boolean mode) { 
        this.prettyPrinting = mode; 
    }
}