package Duke.Exceptions;

/**
 * Deals with no time situation when write deadline or event tasks.
 */
public class NoTimeException extends DukeException{
    public NoTimeException(String task) {
        super(String.format("  ☹ OOPS!!! The time of a %s cannot be empty.", task));
    }
}
