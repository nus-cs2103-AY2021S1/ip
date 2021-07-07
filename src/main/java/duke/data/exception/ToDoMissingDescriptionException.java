package duke.data.exception;

/**
 * Custom exception thrown when To Do command is missing a description.
 */
public class ToDoMissingDescriptionException extends DukeException {
    public ToDoMissingDescriptionException() {
        super("duke.data.task.ToDo task needs a description :)");
    }
}
