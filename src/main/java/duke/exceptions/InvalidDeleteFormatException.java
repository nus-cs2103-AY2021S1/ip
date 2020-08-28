package duke.exceptions;

/**
 * Class to initiate InvalidDeleteFormatException.
 * Thrown when the format for delete command is wrong.
 */
public class InvalidDeleteFormatException extends DukeException {
    public InvalidDeleteFormatException() {
        super("☹ OOPS!!! Format for delete command doesnt seem to be correct. Eg. delete 2");
    }
}
