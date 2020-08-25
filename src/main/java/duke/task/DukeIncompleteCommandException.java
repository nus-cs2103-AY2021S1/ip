package duke.task;

public class DukeIncompleteCommandException extends DukeRunTimeException {
    public DukeIncompleteCommandException(String errorMessage) {
        super(errorMessage);
    }
}
