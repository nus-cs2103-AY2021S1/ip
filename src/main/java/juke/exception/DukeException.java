package juke.exception;

/**
 * Represents a general Exception specific to the Duke Chatbot.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with a given message.
     * @param message Error Message to be output to the user.
     */
    public DukeException(String message) {
        super(message);
    }
}
