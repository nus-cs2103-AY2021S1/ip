package duke.task;

import duke.DukeException;

/**
 * Represents a ToDo Task.
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    /**
     * Convert Task into the saved format.
     * @return A String that described the Task in saved format.
     */
    @Override
    public String toSaveFormat() {
        return "T" + super.toSaveFormat();
    }

    /**
     * Convert Task into the display format.
     * @return A String that described the Task in display format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
