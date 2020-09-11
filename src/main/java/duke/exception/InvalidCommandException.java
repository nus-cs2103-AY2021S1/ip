package duke.exception;

public class InvalidCommandException extends DukeException {

    public InvalidCommandException() {
        super("Hey buddy, that's an invalid command.\n" +
                "Type help to see the available commands");
    }

    public String toString() {
        return getMessage();
    }
}
