package duke.exceptions;

/**
 * Exception thrown when user inputs a command that is not included in the list of valid commands.
 */
public class UnknownCommandException extends DukeException {

    public UnknownCommandException(String command) {
        super(String.format("Unknown command: %s", command),
                "OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
