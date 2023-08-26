package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ReadFile is used to read in a file from a given directory.
 *
 * @author Joseph Beck
 * @version 0.1
 * @since   2023-02
 */
public class ReadFile {

    /**
     * Stores the directory.
     */
    private String directory;
    /**
     * Stores the file contents.
     */
    private List<String> contents;

    /**
     * Initializes the ReadFile object and assigns directory.
     *
     * @param String directory
     * @return none
     */
    public ReadFile(String directory) {
        this.directory = directory;
        this.contents = new ArrayList<String>();
        readFile();
    }

    /**
     * Checks if a file exists.
     *
     * @return boolean
     */
    public boolean checkFileExists() {
        if (directory == null || directory == "") return false;

        File file = new File(directory);
        return file.exists();
    }

    /**
     * Checks if the list of contents is empty.
     *
     * @return boolean
     */
    public boolean checkContentsEmpty() {
        return contents.isEmpty() || contents.size() == 0;
    }

    /**
     * Reads the file using the directory.
     * Adds each line that isn't null to the contents.
     *
     * @exception IOException
     * @return none
     */
    public void readFile() {
        if (!checkFileExists()) return;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(directory))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                contents.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the file contents.
     *
     * @return List<String>
     */
    public List<String> getFileContents() {
        return this.contents;
    }

    /**
     * Gets a random element from the list.
     *
     * @return String
     */
    public String getRandomElement() {
        if (contents.size() == 0) return null;

        Random random = new Random();
        int index = random.nextInt(contents.size());
        return contents.get(index);
    }

    /**
     * Gets the directory.
     *
     * @return String
     */
    public String getDirectory() {
        return this.directory;
    }

    /**
     * Sets the directory.
     *
     * @param String directory
     * @return none
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }
}