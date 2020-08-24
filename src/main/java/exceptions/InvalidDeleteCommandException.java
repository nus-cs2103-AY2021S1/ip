package exceptions;

public class InvalidDeleteCommandException extends DukeException {

    public InvalidDeleteCommandException() {
        super("☹ OOPS!!! Please mark a valid item for deletion");
    }
}
