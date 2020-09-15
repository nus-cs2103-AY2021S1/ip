package duke.exception;

public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super("Error! Invalid date format, Please enter the date in the format dd-MM-yyyy HH:mm");
    }
    public InvalidDateException(String message) {
        super(message);
    }
}
