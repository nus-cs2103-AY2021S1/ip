package duke.task;

/**
 * Encapsulates a Todo Task which contains the details of the task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo Task by providing information such as whether the task
     * is done and the task name.
     */
    public Todo(String isCompleted, String taskName) {
        super("T", isCompleted, taskName);
    }

    @Override
    public String getDateAndTime() {
        return "";
    }

    /**
     * Overrides the Object toString() method.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
