package dude.util;

/**
 * CorruptedClass class to denote an issue with the format of the data file.
 */
public class CorruptedFileException extends Exception {
    /**
     * Constructor for the CorruptedFileException class.
     * @param message the error message.
     */
    public CorruptedFileException(String message) {
        super(message);
    }
}
