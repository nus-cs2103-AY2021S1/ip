package duke.task;

/**
 * Represents a task to be done without a deadline.
 */
public class Todo extends Task {

    public Todo(String desc) {
        super("T", desc);
    }

    @Override
    public String formatTaskForFile() {
        return taskType + " | " + (isDone ? "1" : "0")
                    + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + description;
    }
}
