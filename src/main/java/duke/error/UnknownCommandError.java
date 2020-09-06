package duke.error;

public class UnknownCommandError extends DukeError {
    public UnknownCommandError() {
        super("Unknown command!\nPlease type 'help' for the list of commands!");
    }

}
