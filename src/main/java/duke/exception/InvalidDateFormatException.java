package duke.exception;

public class InvalidDateFormatException extends DukeException {

    public InvalidDateFormatException() {
        super("\u2639 OOPS!!! Your date format is wrong! Make sure it is in yyyy-mm-dd format, eg. 2019-10-15");
    }
}
