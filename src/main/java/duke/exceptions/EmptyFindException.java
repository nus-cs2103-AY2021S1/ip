package duke.exceptions;

/**
 * Thrown when no search term is inputted.
 */
public class EmptyFindException extends DukeException {
    public EmptyFindException() {
        super("Please input a search term");
    }
}
