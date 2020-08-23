package duke.exception;

/**
 * Encapsulates the exception when there is an attempt to create a new task, but insufficient
 * details are provided for the task.
 */
public class MissingTaskDetailsException extends DukeException {
    /**
     * Initialises a new instance with the specified detail message followed by a warning that
     * there are insufficient details.
     *
     * @param message The detail message.
     */
    public MissingTaskDetailsException(String message) {
        super(message + " Insufficient details provided!");
    }

    /**
     * Initialises a new instance with the specified detail message as well as the missing details.
     *
     * @param message        The detail message.
     * @param missingDetails The task details that are missing
     */
    public MissingTaskDetailsException(String message, String missingDetails) {
        super(message + " " + missingDetails);
    }
}
