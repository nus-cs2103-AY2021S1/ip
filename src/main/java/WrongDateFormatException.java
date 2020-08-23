/**
 * Custom exception to handle the case where a wrong date format is inputed
 * @author vanGoghhh
 */

public class WrongDateFormatException extends DukeException {

    public WrongDateFormatException() {
        super("Incorrect input date format");
    }
}
