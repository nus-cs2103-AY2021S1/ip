/**
 * The Task class is the abstract class for deadline, event and todos.
 * It has the task name, and can be marked as done.
 */
public abstract class Task {
    protected boolean isCompleted = false;
    protected String name;

    protected Task (String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        String completionStatus = isCompleted ? "[✓]" : "[✗]";
        return completionStatus + " " + name;
    }

    /**
     * Converts a todo to a string format to be written into a file
     * @return string format to be written into a file
     */
    public String toSaveFormat() {
        String completionStatus = isCompleted ? "1" : "0";
        return completionStatus + " | " + name;
    }
}
