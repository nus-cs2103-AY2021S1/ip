package duke.exceptions;

public class EmptyTaskDescriptionException extends IllegalArgumentException {

    public EmptyTaskDescriptionException(String taskType) {
        super(String.format("OOPS! The description of a %s cannot be empty.", taskType));
    }

}
