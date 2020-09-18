/**
 * ToDo is the simplest kind of Task.
 */
public class ToDo extends Task {

    public ToDo(String description, boolean isDone, Priority priority) {
        super(description, TaskType.TODO, isDone, priority);
    }

    @Override
    public String getSavedString() {
        return super.getSavedString() + " | " + (priority == null ? "-" : priority.toString());
    }

    @Override
    public String toString() {
        return super.toString() + (priority == null ? "" : " (priority: " + priority.toString() + ")");
    }
}
