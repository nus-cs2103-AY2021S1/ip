package duke;

/**
 * Creates a type of task called todo which doesn't have a specified time.
 */
public class Todo extends Task  {
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * Returns the string representation of a todo
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
