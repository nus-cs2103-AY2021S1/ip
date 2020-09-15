package duke.exception;

public class EmptyIndexException extends DukeException {
    private static final String MESSAGE = "QUACKK!! The task index is empty :'(.";

    @Override
    public String toString() {
        return MESSAGE;
    }
}
