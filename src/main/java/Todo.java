/**
 * A Deadline class that represents a Todo.
 * It has a Task description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the representative text of the Todo.
     * @return Representative text.
     */
    @Override
    public String taskSaver() {
        String type = "T";
        return type + "/" + super.taskSaver();
    }

    /**
     * Outputs the Todo as a String.
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
