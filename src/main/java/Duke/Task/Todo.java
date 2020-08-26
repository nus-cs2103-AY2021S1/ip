package Duke.Task;

/**
 * Represents a to-do item without timing
 */
public class Todo extends Task {

    /**
     * Construct a to-do item.
     * @param description title of the task.
     * @param isDone status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Represents the string written into data.txt.
     * @return A string written into the data.txt.
     */
    @Override
    public String toWrite() {
        return super.toWrite();
    }
}
