package duke.error;

public class UnknownCommandError extends DukeError {
    public UnknownCommandError() {
        super("Unknown command!\nPlease insert the task type first before typing in your task!");
    }
}
