package duke.task;

/**
 * A To-do is a Task to be done some time in the future.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getDescriptionForDatabase() {
        return "todo - " + (isTaskDone() ? "1" : "0")
                + " - " + getDescription();
    }
}
