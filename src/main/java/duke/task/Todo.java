package duke.task;

/**
 * Represents a Todo task that contains a task name.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task containing the description of the task.
     *
     * @param name Description of the Todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns the string representation of a Todo task to be appended to a local file.
     *
     * @return String representation of a Todo task with the description and done status.
     */
    public String appendFile() {
        String doneString = (isDone() ? "1" : "0");
        return "todo" + " | " + doneString + " | " + getName();
    }

    /**
     * Returns the string representation of a Todo task.
     *
     * @return String representation of a Todo task containing the description and done status.
     */
    @Override
    public String toString() {
        String doneString = (isDone() ? "✓" : "✗");
        return "[T]" + "[" + doneString + "] " + getName();
    }
}
