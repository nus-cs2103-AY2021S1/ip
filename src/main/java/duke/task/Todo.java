package duke.task;

/**
 * Supports the creation of Todo objects.
 */
public class Todo extends Task {

    /**
     * Creates a Todo object
     * @param description Description of task.
     * @param taskType Type of task.
     */
    public Todo(String description, TaskType taskType) {
        super(description, taskType);
    }

    /**
     * Creates a Todo object with extra parameter that defines whether
     * task is done or not.
     * @param description Description of task.
     * @param taskType Type of task.
     * @param isDone Whether task is done or not.
     */
    public Todo(String description, TaskType taskType, boolean isDone) {
        super(description, taskType, isDone);
    }

    /**
     * Returns string representation of the Todo object.
     * @return String representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[Todo]" + super.toString() + " " + "- Priority: " + super.getPriorityLevel();
    }
}
