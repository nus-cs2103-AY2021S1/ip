package duke.exception;

public class DukeException extends Exception {
    protected String command;

    public DukeException(String command) {
        super("Error caused by input: " + command);
        this.command = command;
    }

    @Override
    public String toString() {
        return "Sorry, I can't understand your command.";
    }
}
