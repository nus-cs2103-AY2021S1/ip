package duke.task;

/**
 * Class to add a ToDo, which is a Task.
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo.
     *
     * @param description Description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Reformat the ToDo from existing data.
     *
     * @return Reformatted ToDo for writing to storage.
     */
    public String formatToDo() {
        return String.format("T | %d | %s", this.getIsDone() ? 1 : 0,
                this.getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}