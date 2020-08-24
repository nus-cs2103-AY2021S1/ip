package exceptions;

public class InvalidDoneCommandException extends DukeException {
    public InvalidDoneCommandException() {
        super("☹ OOPS!!! Please mark a valid item as done");
    }
}
