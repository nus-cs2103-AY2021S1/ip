package duke.command;

/**
 * Throws when the user input of command cannot be parsed correctly.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public InvalidCommandException(String msg) {
        super(msg);
    }
}
