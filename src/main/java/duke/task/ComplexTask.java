package duke.task;

/**
 * Stores properties of the more complex tasks - EVENT and DEADLINE.
 */
public class ComplexTask extends Task {

    private final String time;
    private final TaskType taskType;

    /**
     * Initializes the complex task.
     *
     * @param description Description of the task.
     * @param taskType Type of task, either EVENT or DEADLINE.
     * @param time Time of the task.
     */
    public ComplexTask(String description, TaskType taskType, String time) {
        super(description, false, taskType, time);
        this.time = time;
        this.taskType = taskType;
    }

    /**
     * Initializes the complex task with isDone boolean value.
     *
     * @param description Description of the task.
     * @param isDone Boolean to represent if task is done or not.
     * @param taskType Type of task, either EVENT or DEADLINE.
     * @param time Time of the task.
     */
    public ComplexTask(String description, boolean isDone, TaskType taskType, String time) {
        super(description, isDone, taskType, time);
        this.time = time;
        this.taskType = taskType;
    }

    /**
     * Provides the string representation of the complex task.
     *
     * @return String representation of the complex task.
     */
    @Override
    public String toString() {
        if (taskType == TaskType.EVENT) {
            return "[E]" + super.toString() + " (at: " + time + ")";
        }
        if (taskType == TaskType.DEADLINE) {
            return "[D]" + super.toString() + " (by: " + time + ")";
        }
        assert false : "Invalid complex task!";
        return null;
    }
}
