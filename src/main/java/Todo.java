/**
 * A Todo task that comprises a task description and whether it is done.
 */
public class Todo extends Task {

    /**
     * Create a Todo task.
     * @param description of task.
     * @param isDone done status of task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the String description for Todo task.
     * @return String description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
