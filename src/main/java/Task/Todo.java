package task;

/**
 * This class creates the task as a Todo_task. This task has no set date to occur.
 *
 * @author Joshua
 */
public class Todo extends Task {
    /**
     * Creates the Todo_task.
     *
     * @param taskDescription the contents of the task.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Formats the Todo_Task into the format it is stored as.
     *
     * @return the formatted Todo_task.
     */
    @Override
    public String saveFormat() {
        String base = "[T] ";
        if (taskCompleted) {
            base = base + "[✓]";
        } else {
            base = base + "[✗]";
        }
        base = base + taskDescription;
        return base;
    }

    /**
     * Returns the Todo_task to the ui to be displayed to the user.
     *
     * @return a String that contains the Todo_task in the display format.
     */
    @Override
    public String toString() {
        String base = "[T] ";
        if (taskCompleted) {
            base = base + "[✓]";
        } else {
            base = base + "[✗]";
        }
        base = base + taskDescription;
        return base;
    }
}
