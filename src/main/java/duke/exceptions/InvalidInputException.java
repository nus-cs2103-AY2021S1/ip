package main.java.duke.exceptions;

/**
 * InvalidInputException is thrown when a command has an incorrect
 * input.
 */
public class InvalidInputException extends DukeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
