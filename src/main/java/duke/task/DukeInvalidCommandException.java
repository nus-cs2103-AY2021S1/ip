package duke.task;

public class DukeInvalidCommandException extends DukeRunTimeException {
    public DukeInvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
