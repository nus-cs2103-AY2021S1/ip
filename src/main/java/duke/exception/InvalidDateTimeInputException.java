package duke.exception;

public class InvalidDateTimeInputException extends DukeException {
    public InvalidDateTimeInputException() {
        super("Sorry please enter a valid date and time!");
    }
}
