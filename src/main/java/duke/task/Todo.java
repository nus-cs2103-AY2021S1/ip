package duke.task;

/**
 * Represents a to do task with description.
 * Inherits from Task.
 */
public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Converts the task to a string for saving.
     *
     * @return String representing a task in save file.
     */
    @Override
    public String toSaveFormat() {
        return "todo " + super.toSaveFormat();
    }

    /**
     * Converts the task to a string indicating type of task, done, description and time (if applicable).
     *
     * @return String representing task in user interface.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
