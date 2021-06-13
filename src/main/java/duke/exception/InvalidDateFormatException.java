package duke.exception;

public class InvalidDateFormatException extends DukeException {
    private static final String MESSAGE_NO_TIME = "QUACK QUACK!! Entered date is in the wrong format. Please "
            + "specify in this format YYYY-MM-DD";
    private static final String MESSAGE_WITH_TIME = "QUACK QUACK!! Entered date is in the wrong format. Please "
            + "specify in this format YYYY-MM-DD HH:MM";
    private boolean hasTime;

    public InvalidDateFormatException(boolean hasTime) {
        this.hasTime = hasTime;
    }

    @Override
    public String toString() {
        return !hasTime ? MESSAGE_NO_TIME : MESSAGE_WITH_TIME;
    }
}
