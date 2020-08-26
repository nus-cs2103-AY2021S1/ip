package exceptions;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("☹ OOPS!!! Sorry I do not understand this command!");
    }
}
