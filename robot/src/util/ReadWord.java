package util;

/**
 * Extends the ReadFile to make getting a random word a bit more simple.
 *
 * @author Joseph Beck
 * @version 0.1
 * @since   2023-02
 */
public class ReadWord extends ReadFile {

    /**
     * Initializes the superclass with the given directory.
     *
     * @param String directory
     * @return non
     */
    public ReadWord(String directory) {
        super(directory);
    }

    /**
     * Returns a random word from the list of contents.
     *
     * @return String
     */
    public String getRandomWord() {
        return super.getRandomElement();
    }
}
