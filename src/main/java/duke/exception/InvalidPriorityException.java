package duke.exception;

public class InvalidPriorityException extends DukeException {
    private static final String MESSAGE = "QUACK! Priority can be one of high, medium or low";

    @Override
    public String toString() {
        return MESSAGE;
    }
}
