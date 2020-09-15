package duke.exception;

public class EmptyDateException extends DukeException {
    private static final String MESSAGE = "QUACK!!! Please specify the date";

    @Override
    public String toString() {
        return MESSAGE;
    }
}
