/**
 * Represents a task to be done.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Mark the ToDo task as done.
     *
     * @return A ToDo task that is done.
     */
    @Override
    public ToDo markAsDone() {
        ToDo doneToDo = new ToDo(this.description, true);
        return doneToDo;
    }

    @Override
    public String toTxtFileFormat() {
        return "T" + super.toTxtFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
