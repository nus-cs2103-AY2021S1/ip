package duke.exceptions;

public class EmptyTaskDescriptionException extends DukeException {

    public EmptyTaskDescriptionException(String taskType) {
        super(String.format("OOPS! The description of a %s cannot be empty.", taskType));
    }

}
