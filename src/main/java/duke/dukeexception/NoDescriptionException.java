package duke.dukeexception;

public class NoDescriptionException extends DukeException {

    public NoDescriptionException(String commandName) {
        super("The description of " + commandName + " cannot be empty lah. Try again!");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
