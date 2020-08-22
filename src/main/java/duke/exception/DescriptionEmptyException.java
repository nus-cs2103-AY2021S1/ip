package duke.exception;

/**
 * Represents the exception that the description of a task is empty.
 */
public class DescriptionEmptyException extends DukeException{
    public DescriptionEmptyException(String type) {
        super(String.format("☹ OOPS!!! The description of a %s cannot be empty.",type));
    }
}
