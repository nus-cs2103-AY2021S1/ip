package exception;

public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super("Date is given in the wrong format! Please try again!");
    }

    public InvalidDateException(String message) {
        super(message);
    }
}
