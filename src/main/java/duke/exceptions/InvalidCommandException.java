package duke.exceptions;

public class InvalidCommandException extends IllegalArgumentException {

    public InvalidCommandException() {
        super("OOPS! I'm sorry but I don't know what that means :-(");
    }
}
