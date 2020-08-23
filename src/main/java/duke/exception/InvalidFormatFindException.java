package duke.exception;

public class InvalidFormatFindException extends DukeException {
    public InvalidFormatFindException() {
        super(" ☹ OOPS! A proper find format would be like, e.g. find \'keyword\' " +
                "(Note that only 1 keyword is allowed.)");
    }
}
