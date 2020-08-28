package duke.exception;


public class InvalidFormatDeleteException extends DukeException {
    private static final String DELETE_EXCEPTION = " ☹ OOPS! A proper delete format would be like e.g. delete "
            + "\'an integer that is between 1(if list is not empty) to the number of items in the list\'";
    public InvalidFormatDeleteException() {
        super(DELETE_EXCEPTION);
    }
}
