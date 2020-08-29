package duke.exception;

/**
 * An exception that occurs when an index Duke receives is invalid
 * (less than 0, or larger than the task list size.
 */
public class InvalidIndexException extends DukeException {

    /**
     * Constructs an InvalidIndexException.
     *
     * @param listSize The current size of the task list.
     */
    public InvalidIndexException(int listSize) {
        super("Invalid index.\n"
            + "You have " + listSize + " items in your list.");
    }
}
