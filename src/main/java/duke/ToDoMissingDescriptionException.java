package duke;

/**
 * Custom exception thrown when To Do command is missing a description.
 */
public class ToDoMissingDescriptionException extends DukeException {
    public ToDoMissingDescriptionException() {
        super("duke.ToDo task needs a description :)");
    }
}
