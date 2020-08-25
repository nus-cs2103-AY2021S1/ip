package duke.task;

/**
 * The class Task denotes a task.
 *
 * @author Alvin Chee
 */
public class Task {
    private String taskInfo;
    private TaskType taskType;
    protected boolean done;
    public static final int undoneNo = 0;
    public static final int doneNo = 1;

    /**
     * Constructs a task
     *
     * @param taskInfo  Task description information.
     * @param taskType  Type of event.
     */
    public Task(String taskInfo, TaskType taskType) {
        this.taskInfo = taskInfo;
        this.taskType = taskType;
    }

    /**
     * Returns a done events task.
     *
     * @return A done events task.
     */
    public Task doneTask() {
        this.done = true;
        return this;
    }

    /**
     * Returns task type of event.
     *
     * @return Task type of event.
     */
    public TaskType returnTaskType() {
        return taskType;
    }

    /**
     * Returns integer representing status of event.
     *
     * @return Integer representing status of event.
     */
    public int returnDoneStatus() {
        return done ? doneNo : undoneNo;
    }

    /**
     * Returns description of task.
     *
     * @return Description of task.
     */
    public String returnTaskInfo() {
        return taskInfo;
    }

    @Override
    public String toString() {
        String status = done ? "[✓]" : "[✗]";
        return taskType + status + " " + taskInfo;
    }
}