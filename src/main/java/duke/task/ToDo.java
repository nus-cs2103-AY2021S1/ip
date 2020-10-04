package duke.task;

import duke.exception.DukeException;

/**
 * Class representing a todo.
 */
public class ToDo extends Task {
    /**
     * Creates a brand new {@code ToDo}.
     *
     * @param description Description of the todo.
     */
    public ToDo(String description) {
        super(description);
        this.taskType = "T";
    }

    private ToDo(String[] data) throws DukeException {
        super(data);
    }

    /**
     * Loads and initializes a {@code ToDo} with pre-existing data.
     *
     * @param data Todo data.
     * @return A Todo task containing the data provided.
     * @throws DukeException If data provided is of an invalid form.
     */
    public static ToDo loadFromData(String[] data) throws DukeException {
        return new ToDo(data);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
