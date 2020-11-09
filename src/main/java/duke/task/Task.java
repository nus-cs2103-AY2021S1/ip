package duke.task;

import java.time.LocalDateTime;

/**
 * Class to represent possible Tasks created by the user
 * @author Kor Ming Soon
 */
public class Task {

    protected boolean isDone;
    protected String task;
    protected TaskType taskType;
    protected LocalDateTime duration;

    /**
     * Constructor for Task.
     * @param task Details of a task.
     * @param taskType Type of a task
     * @param duration Time given to the task.
     */
    public Task(String task, TaskType taskType, LocalDateTime duration) {
        this.task = task;
        this.taskType = taskType;
        this.isDone = false;
        this.duration = duration;
    }

    /**
     * Overloaded constructor for a Task.
     * @param task Details of a task.
     * @param taskType Type of a task
     * @param duration Time given to the task.
     * @param isDone The completion of a task.
     */
    public Task(String task, TaskType taskType, LocalDateTime duration, boolean isDone) {
        this.task = task;
        this.taskType = taskType;
        this.isDone = isDone;
        this.duration = duration;
    }

    /**
     * Determines if the tick or cross will be assigned to a task.
     * Tick for completed task and vice versa.
     * @return ASCII Tick or Cross
     */
    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
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
     * Retrieves the LocalDate
     * @return LocalDate
     */
    public LocalDateTime getDuration() {
        return this.duration;
    }
    /**
     * Retrives the type of tasking that was recorded.
     * @return Type of task.
     */
    public TaskType getTasktype() {
        return taskType;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.taskType, getStatusIcon(), this.task);
    }

}
