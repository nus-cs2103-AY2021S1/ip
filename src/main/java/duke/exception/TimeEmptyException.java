package duke.exception;

public class TimeEmptyException extends DukeException {
    public TimeEmptyException(String type) {
        super(String .format(" ☹ OOPS!!! The time of a %s cannot be empty.",type));
    }
}
