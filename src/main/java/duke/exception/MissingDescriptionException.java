package duke.exception;

/**
 * Represents the exception when the user forgets to write the description
 * for the tasks.
 */
public class MissingDescriptionException extends DukeException {
    public MissingDescriptionException(String taskType) {
        super("The description of the " + taskType + " is missing (¬ ¬ )");
    }
}
