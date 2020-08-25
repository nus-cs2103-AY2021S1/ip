package duke;

/**
 * Class to represent possible Tasks created by the user
 * @author Kor Ming Soon
 */
public class Task {

    protected boolean isDone;
    protected String task;
    protected Tasktype tasktype;
    protected String duration;

    /**
     * Constructor for Task.
     * @param task Details of a task.
     * @param tasktype Type of a task
     * @param duration Time given to the task.
     */
    public Task(String task, Tasktype tasktype, String duration) {
        this.task = task;
        this.tasktype = tasktype;
        this.isDone = false;
        this.duration = duration;
    }

    /**
     * Overloaded constructor for a Task.
     * @param task Details of a task.
     * @param tasktype Type of a task
     * @param duration Time given to the task.
     * @param isDone The completion of a task.
     */
    public Task(String task, Tasktype tasktype, String duration, boolean isDone) {
        this.task = task;
        this.tasktype = tasktype;
        this.isDone = isDone;
        this.duration = duration;
    }

    /**
     * Determines if the tick or cross will be assigned to a task.
     * Tick for completed task and vice versa.
     * @return ASCII Tick or Cross
     */
    public String getStatusIcon() {
        return isDone ? "✓" : "✘";
    }

    /**
     * Changes the completion status of a task to be completed.
     */
    public void makeDone() {
        this.isDone = true;
    }

    /**
     * Checks if a task is completed or not.
     * @return boolean value if task is completed or not.
     */
    public boolean isTaskDone() {
        return this.isDone;
    }

    /**
     * Retrieves the details of the task.
     * @return Details of the task.
     */
    public String getTask() {
        return task;
    }

    /**
     * Retrieves the timing or duration which was assigned to the task.
     * @return The timing or duration assigned to the task.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Retrives the type of tasking that was recorded.
     * @return Type of task.
     */
    public Tasktype getTasktype() {
        return tasktype;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.tasktype, getStatusIcon(), this.task);
    }

}
