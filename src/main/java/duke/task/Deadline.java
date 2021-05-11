package duke.task;

/**
 * Defines a deadline
 */
public class Deadline extends Task {
    public Deadline(String taskName) {
        super(taskName, TaskType.D);
    }
    public Deadline(String taskName, Priority priority) {
        super(taskName, TaskType.D, priority);
    }
}
