package duke.exception;

public class MissingTaskDetailsException extends DukeException {
    public MissingTaskDetailsException(String message) {
        super(message + "Insufficient details provided!");
    }

    public MissingTaskDetailsException(String message, String missingDetails) {
        super(message + missingDetails);
    }
}
