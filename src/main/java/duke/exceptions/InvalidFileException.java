package main.java.duke.exceptions;

/**
 * InvalidFileException is thrown when the file fails to save or load.
 */
public class InvalidFileException extends DukeException {
    public InvalidFileException(String message) {
        super(message);
    }
}
