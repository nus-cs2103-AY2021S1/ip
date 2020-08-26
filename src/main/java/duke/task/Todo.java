package duke.task;

/**
 * Encapsulates a Todo Task which contains the details of the task.
 */
public class Todo extends Task {
    private String type = "T";

    /**
     * Constructs a Todo Task by providing information such as whether the task
     * is done and the task name.
     */
    public Todo(String isCompleted, String taskName) {
        super(isCompleted, taskName);
    }

    /**
     * Overrides the Object toString() method.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
