package duke.exceptions;

public class InvalidInputException extends DukeException {
    private static final String ERROR_MSG = "Sorry! The input is not valid.";
    public InvalidInputException() {
        super(ERROR_MSG);
    }
    public InvalidInputException(String errMsg) {
        super(errMsg);
    }

}
