/**
 * Represents a task to be done without a deadline.
 */
public class Todo extends Task {

    Todo(String desc) {
        super("T", desc);
    }

    @Override
    public String formatTaskForFile() {
        return this.taskType + " | " +
                (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + this.description;
    }
}
