package duke.exception;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Sorry I don't know what that means. Type \"help\" to view the list of commands available");
    }
}
