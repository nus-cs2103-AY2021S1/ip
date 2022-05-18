package duke.exceptions;

/**
 * Exception thrown when user inputs a command that is not included in the list of valid commands.
 */
public class UnknownCommandException extends DukeException {

    /**
     * Constructs an UnknownCommandException given the unknown command String provided.
     *
     * @param command command String which does not match any valid Commands
     */
    public UnknownCommandException(String command) {
        super(String.format("Unknown command: %s", command),
                "OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
