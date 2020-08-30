package duke.task;

/**
 * Class to represents the ToDoTask object. This task object
 * is used to represent all the task without any date.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a new ToDoTask object with the specified task name.
     * When a new Task object is created, the status is set to ongoing by default.
     *
     * @param taskName The task's name
     */
    public ToDoTask(String taskName) {
        super(taskName);
    }

    /**
     * Returns the string representation of the ToDoTask.
     * ToDoTask is represented with "T" in front.
     *
     * @return a string representation of the ToDoTask.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
