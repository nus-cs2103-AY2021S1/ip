package Duke.Exception;

public class InvalidDateTimeException extends DukeException {

    public InvalidDateTimeException() {
        super("Invalid Date and Time input."
                + " Follow this format: \"YYYY/MM/DD HHmm\"");
    }
}
