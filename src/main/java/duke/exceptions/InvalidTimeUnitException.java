package duke.exceptions;

public class InvalidTimeUnitException extends DukeException {

    public InvalidTimeUnitException() {
        super("Invalid time unit specified");
    }
}
