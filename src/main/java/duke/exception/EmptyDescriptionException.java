package duke.exception;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String taskType) {
        super("Error! '" + taskType + "' description cannot be empty.");
    }
}
