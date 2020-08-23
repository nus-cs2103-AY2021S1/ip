package duke.exceptions;

public class UnknownCommandException extends DukeException {

    public UnknownCommandException(String command) {
        super(String.format("Unknown command: %s", command),
                "OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
