package duke.task;

/**
 * Represents a TODO DukeTask.
 * Similar to the generic <code>DukeTask</code>, it only contains a description of what to do.
 */
public class TodoTask extends DukeTask {
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
