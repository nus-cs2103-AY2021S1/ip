package duke.exception;

public class InvalidDateInputException extends DukeException {
    public InvalidDateInputException(String date) {
        super(String.format("%s is not a recognised date format. " + "Please key in dates in the "
                + "format yyyy-MM-dd. " + "For example, 2007-12-03.", date));
    }
}
