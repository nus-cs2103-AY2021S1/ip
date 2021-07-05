package exception;

/**
 * Exception when the description of the task is empty.
 */

public class DescriptionException extends DukeException {
    private String command;
    public DescriptionException(String command) {
        this.command = command;
    }

    public String toString() {
        return "Oops! The description for " + this.command + " cannot be empty!";
    }
}
