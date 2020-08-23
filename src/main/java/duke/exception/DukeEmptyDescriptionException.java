package duke.exception;

public class DukeEmptyDescriptionException extends DukeTaskException {
    public DukeEmptyDescriptionException(String task) {
        super(String.format("☹ OOPS!!! The description of %s cannot be empty.", task));
    }
}
