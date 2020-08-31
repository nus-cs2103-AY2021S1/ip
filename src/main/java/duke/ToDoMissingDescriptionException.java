package duke;

public class ToDoMissingDescriptionException extends DukeException {
    public ToDoMissingDescriptionException() {
        super("duke.ToDo task needs a description :)");
    }
}
