package duke.exception;

public class InvalidDeadlineException extends DukeException{

    public InvalidDeadlineException() {
        super("☹ OOPS!!! Your deadline command format is wrong!");
    }
}
