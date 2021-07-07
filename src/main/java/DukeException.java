/**
 * Represents a Duke exception object that could occur due to an invalid user input when running the chatbot.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
