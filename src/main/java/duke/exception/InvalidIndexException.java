package duke.exception;

public class InvalidIndexException extends DukeException {
    private static final String MESSAGE = "QUACK!!! The task index is invalid :'(";

    @Override
    public String toString() {
        return MESSAGE;
    }
}
