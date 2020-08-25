package duke.task;

public class ToDo extends Task {
    /**
     * Initializes a ToDo task with a description.
     *
     * @param description The description for the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
