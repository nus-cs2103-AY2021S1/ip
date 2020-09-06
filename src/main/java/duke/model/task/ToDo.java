package duke.model.task;

/**
 * Class for ToDo type of Task.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo-type Tasks.
     *
     * @param description Description of task input by user.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for ToDo-type Tasks loaded from memory.
     *
     * @param description Description of task.
     * @param isDone Current state of task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Generates String with relevant dat to save to disk.
     *
     * @return String that contains data to save.
     */
    public String toDataString() {
        return String.format("T|%s|%s", super.isDone, super.description);
    }
}
