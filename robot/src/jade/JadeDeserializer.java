package jade;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The JadeDeserializer is used to convert the contents of a file or a string to a new JadeObject.
 * <p> 
 * Structure of an example .jde file:
 * <p>
 * <i>variableOne: 'valueOne';</i>
 * <p>
 * <i>variableTwo: 'valueTwo';</i>
 * <p>
 * <i>:</i> -> used to split the variable and its value.
 * <p>
 * <i>;</i> -> to denote the end of a varialbe.
 * <p>
 * <i>'</i> -> surrounds a value of a variable.
 *
 * @author  Joseph Beck
 * @version 0.1
 * @since   2023
 */
public class JadeDeserializer {

    /**
     * Empty JadeDeserializer constructor, it has no variables that requitre initialization.
     * 
     * @return none
     */
    public JadeDeserializer() { }

    /**
     * Deserializes a string from a given file into a JadeObject.
     * The file and its strings require a large amount of parsing for correct JadeObject creation.
     * New lines are removed, data is split based on ; and pairs are determined by : and ''.
     * 
     * @param String directory
     * @return JadeObject
     */
    public JadeObject deserializeFromFile(String dir) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dir))) {
            String line = reader.readLine();
            String data = "";

            while (line != null) {
                data += line;
                line = reader.readLine();
            }
            
            data.replaceAll("\\s+" , "");
            String[] elements = data.split(";");
            JadePair[] dataPoints = new JadePair[elements.length];

            for (int i = 0; i < elements.length; i++) {
                String[] tuple = elements[i].split(":");
                dataPoints[i] = makeJadePair(tuple);
            }
            
            JadeObject obj = new JadeObject(dataPoints);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
            return null;   
        }
    }

    /**
     * Deserializes a given string.
     * The string itself is first parsed removeing any unnceresary artifcats.
     * It is split identically to the file splitting.
     * 
     * @param String jade string
     * @return JadeObject
     */
    public JadeObject deserializeFromString(String jadeString) {
        jadeString.replaceAll("\\s+" , "");
        String[] elements = jadeString.split(";");
        JadePair[] dataPoints = new JadePair[elements.length];
        
        for (int i = 0; i < elements.length; i++) {
            String[] tuple = elements[i].split(":");
            dataPoints[i] = makeJadePair(tuple);
        }
        
        JadeObject obj = new JadeObject(dataPoints);
        return obj;
    }

    /**
     * Creates a JadePair from a string array of elements that should be of length 2.
     * Each element is also correctly parsed to ensure there are no left over artifacts.
     * 
     * @param String[] elements
     * @return JadePair
     */
    private JadePair makeJadePair(String[] elements) {
        String first = elements[0]
            .replaceAll("\"", "")
            .replaceAll("\\s+", "")
            .replaceAll("'", "");

        String second = elements[1]
            .replaceAll("\"", "")
            .replaceAll("\\s+", "")
            .replaceAll("'", "");
    
        return new JadePair(first, second);
    }
}