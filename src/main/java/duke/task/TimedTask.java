package duke.task;

/**
 * The class TimedTask denotes a timed task.
 *
 * @author Alvin Chee
 */
public abstract class TimedTask extends Task {

    /**
     * Constructs a timed task
     *
     * @param taskInfo Task description information.
     * @param taskType Type of event.
     */
    public TimedTask(String taskInfo, TaskType taskType) {
        super(taskInfo, taskType);
    }

    /**
     * Returns string description of deadline.
     *
     * @return String description of deadline.
     */
    public abstract String returnTime();
}
