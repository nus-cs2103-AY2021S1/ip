package duke.exception;

public class InvalidFormatFindException extends DukeException {
    private static final String FIND_EXCEPTION = " ☹ OOPS! A proper find format would be like, e.g. find \'keyword\' "
            + "(Note that only 1 keyword is allowed.)";
    public InvalidFormatFindException() {
        super(FIND_EXCEPTION);
    }
}
