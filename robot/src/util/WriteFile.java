package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Writes to a given file.
 *
 * @author Joseph Beck
 * @version 0.1
 * @since   2023-02
 */
public class WriteFile {

    /**
     * Stores the directory of the file.
     */
    private String directory;

    /**
     * Takes a directory and initalizes the directory variable.
     *
     * @param String directory
     * @return none
     */
    public WriteFile(String directory) {
        this.directory = directory;
    }

    /**
     * Writes a single piece of data to a file.
     *
     * @param String data
     * @return none
     */
    public void writeToFile(String data) {
        writeToFile(new String[] { data });
    }

    /**
     * Writes an array of data to a file.
     *
     * @exception IOException
     * @param String[] data
     */
    public void writeToFile(String[] data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory))) {
            for (String piece : data) {
                writer.write(piece);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}