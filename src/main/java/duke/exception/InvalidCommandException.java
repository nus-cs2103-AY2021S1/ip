package duke.exception;

public class InvalidCommandException extends DukeException {
    private static final String MESSAGE = "QUACK!!! I'm sorry master, but I don't know what that means :'(";

    @Override
    public String toString() {
        return MESSAGE;
    }
}
