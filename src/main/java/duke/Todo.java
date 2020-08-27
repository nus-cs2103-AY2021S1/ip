package duke;

/**
 * Creates a type of task called todo which doesn't have a specified time.
 */
public class Todo extends Task  {
    public Todo(String taskname, boolean status) {
        super(taskname, status);
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
