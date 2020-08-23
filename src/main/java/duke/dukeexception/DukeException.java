package duke.dukeexception;

/**
 * Parent class of exceptions that are thrown by the Duke program.
 */
public class DukeException extends Exception {

    /**
     * Constructor of the Duke exception.
     * @param message Contains the message that would be shown to the user when the exception is thrown
     */
    DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

}
