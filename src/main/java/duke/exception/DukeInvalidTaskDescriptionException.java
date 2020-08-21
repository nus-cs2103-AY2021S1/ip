package duke.exception;

public class DukeInvalidTaskDescriptionException extends DukeTaskException {

    @Override
    public String toString() {
        return "ERROR: The description of a task cannot be empty!";
    }

}
