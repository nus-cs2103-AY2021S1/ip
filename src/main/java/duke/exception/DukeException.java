package duke.exception;

/**
 * Represents the possible exceptions that can be thrown from the {@link duke.Duke} package.
 */
public class DukeException extends Exception {
    /**
     * Instantiates a new DukeException object.
     * @param errorMessage The detail error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
