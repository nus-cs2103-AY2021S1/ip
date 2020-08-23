package duke.error;

public class UnknownCommandError extends DukeError {
    public UnknownCommandError() {
        super("Unknown command! Please insert the task type first before\ntyping in your task!");
    }
}
