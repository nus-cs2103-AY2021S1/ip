package duke.exception;

/**
 * Represents the exception when the user forgets to write or format
 * the deadline for Deadline and Event tasks.
 */
public class MissingDeadlineException extends DukeException{
    public MissingDeadlineException(String taskType) {
        super("The deadline of the " + taskType + " is missing (¬ ¬ )");
    }
}
