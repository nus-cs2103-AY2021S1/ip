package duke.exception;

public class InvalidFormatDoneException extends DukeException {
    private static final String DONE_EXCEPTION = " ☹ OOPS! A proper done format would be like  e.g. done "
            + "\'an integer that is between 1(if list is not empty) to the number of items in the list\'";
    public InvalidFormatDoneException() {
        super(DONE_EXCEPTION);
    }
}
