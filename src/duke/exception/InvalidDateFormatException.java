package duke.exception;

public class InvalidDateFormatException extends DukeException {
    public InvalidDateFormatException() {
        super("Invalid date format! Please put it something like 2020-12-31!");
    }
}
