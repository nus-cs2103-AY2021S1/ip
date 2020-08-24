package duke.exceptions;

/** Thrown to indicate that the user input a task without the description. */
public class EmptyTaskDescriptionException extends DukeException {

    /** Constructs an EmptyTaskDescriptionException with the relevant detail message.
     *
     * @param taskType The task type that the user input.
     */
    public EmptyTaskDescriptionException(String taskType) {
        super(String.format("OOPS! The description of a %s cannot be empty.", taskType));
    }

}
