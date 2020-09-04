package duke.exception;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Type \"help\" to view the list of commands available");
    }
}
