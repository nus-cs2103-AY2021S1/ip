package duke.exception;

public class InvalidTaskDateTimeException extends DukeException{

    public InvalidTaskDateTimeException() {
        super("OOPS!!! Please use the format \"dd/MM/yyyy HH:mm\" to indicate the date and time.");
    }
}
