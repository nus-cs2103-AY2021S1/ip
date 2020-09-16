package duke.task;

/**
 * Supports the creation of duke.task.Todo objects.
 */
public class Todo extends Task {

    /**
     * Creates a duke.task.Todo object
     * @param description Description of task.
     * @param taskType Type of task.
     */
    public Todo(String description, TaskType taskType) {
        super(description, taskType);
    }

    /**
     * Creates a duke.task.Todo object with extra parameter that defines whether
     * task is done or not.
     * @param description Description of task.
     * @param taskType Type of task.
     * @param isDone Whether task is done or not.
     */
    public Todo(String description, TaskType taskType, boolean isDone) {
        super(description, taskType, isDone);
    }

    /**
     * Returns string representation of the duke.task.Todo object.
     *
     * @return String representation of the duke.task.Todo object.
     */
    @Override
    public String toString() {
        return "[Todo]" + super.toString() + " " + "Priority: " + super.getPriorityLevel();
    }
}
