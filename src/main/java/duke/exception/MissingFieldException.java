package duke.exception;

public class MissingFieldException extends DukeException {

    public MissingFieldException(String missingFields) {
        super("Please key in the missing fields",
                MissingFieldException.class.getName(), "Fields Missing - " + missingFields);
    }
}