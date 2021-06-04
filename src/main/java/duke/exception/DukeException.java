package duke.exception;

/**
 * Throwable exception to be caught in other classes and methods
 */

public class DukeException extends Throwable {

    private String message;

    /**
     * Constructor of exception that takes in a specific error message
     *
     * @param message
     */
    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}