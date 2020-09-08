package duke.error;

public class EventDateParseException extends Exception {
    public EventDateParseException(String date) {
        super(String.format("    Please the correct format, %s is not a valid date", date));
    }
}
