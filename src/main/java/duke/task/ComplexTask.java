package duke.task;

/**
 * Stores properties of the more complex tasks - EVENT and DEADLINE.
 */
public class ComplexTask extends Task {

    private String time;
    private TaskType taskType;

    /**
     * Initializes the complex task.
     *
     * @param description description of the task.
     * @param time time of the task.
     * @param taskType type of task, either EVENT or DEADLINE.
     */
    public ComplexTask(String description, String time, TaskType taskType) {
        super(description, false, taskType, time);
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
        } else { // DEADLINE
            return "[D]" + super.toString() + " (by: " + time + ")";
        }
    }
}
