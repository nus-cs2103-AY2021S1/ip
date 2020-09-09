package duke.task;

import duke.exception.DukeException;

/**
 * Class representing a todo.
 */
public class ToDo extends Task {
    /**
     * Creates a brand new {@code ToDo}.
     * @param description Description of the todo.
     */
    public ToDo(String description) {
        super(description);
        this.taskType = "T";
    }

    private ToDo(String[] data) throws DukeException {
        super(data);
    }

    public static ToDo loadFromData(String[] data) throws DukeException {
        return new ToDo(data);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
