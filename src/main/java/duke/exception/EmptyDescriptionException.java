package duke.exception;

/**
 * Represents the exception where no description is entered.
 */
public class EmptyDescriptionException extends DukeException {

    /**
     * Creates an EmptyDescriptionException object.
     */
    public EmptyDescriptionException() {
        super("Oops, the description can't be empty!\n");
    }
}
