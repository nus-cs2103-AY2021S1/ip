package duke;

/**
 *
 */
public class Task {
    protected String taskTitle;
    protected boolean isDone;
    protected TaskTypes taskType;

    /**
     *
     * @param taskTitle
     * @param isDone
     * @param taskType
     */
    public Task(String taskTitle, Boolean isDone, TaskTypes taskType) {
        this.taskTitle = taskTitle;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return (isDone ? "✓" : "✗");
    }

    /**
     *
     * @return
     */
    public String getStatusNum() {
        return (isDone ? "1" : "0");
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "[" + this.taskType + "]" + "[" + getStatus() + "] " + taskTitle;
    }

    /**
     *
     * @return
     */
    public String writeToFile() {
        return this.taskType + " | " + getStatusNum() + " | " + taskTitle;
    }
}
