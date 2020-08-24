package exception;

public class DescriptionException extends DukeException {
    private String command;
    public DescriptionException(String command) {
        this.command = command;
    }

    public String toString() {
        return "Oops! The description for " + this.command + " cannot be empty!";
    }
}
