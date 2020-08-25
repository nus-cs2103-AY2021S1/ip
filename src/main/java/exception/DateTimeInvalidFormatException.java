package exception;

public class DateTimeInvalidFormatException extends InvalidActionException {

    public DateTimeInvalidFormatException() {
        super("Action invalid. Date and Time Format incorrect.");
    }

    public DateTimeInvalidFormatException(String message) {
        super(message);
    }
}
