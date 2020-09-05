package duke.exception;

/**
 * Represents the exception when the task description is missing.
 */
public class DukeMissingTaskDescriptionException extends DukeException {

    private static final String ERROR_MESSAGE = "OOPS!!! Description of a %s cannot be empty :(\n";

    public DukeMissingTaskDescriptionException(String taskType) {
        super(String.format(ERROR_MESSAGE, taskType));
    }
}
