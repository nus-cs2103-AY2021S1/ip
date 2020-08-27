package Task;

import java.time.LocalDateTime;

/**
 * Task is the main functionality of Duke. It is the unit that information given by the user
 * is stored as in Duke and is also how information from Duke is read to the user. It is an
 * abstract class and can only be represented as a Deadline, Event or Todo_task.
 * @author Joshua
 */
public abstract class Task {
    /**
     * position is the location of the task in the TaskList.
     * taskDescription is the contents of the task.
     * taskCompleted is the indicator of whether the task has been done or not.
     * date is the scheduled time for the task to occur.
     */
    protected int position;
    protected String taskDescription;
    protected boolean taskCompleted;
    protected LocalDateTime date;

    /**
     * Creates the task with the given taskDescription. Initializes the task as incomplete.
     * @param taskDescription the description for the task given by the user.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        taskCompleted = false;
    }

    /**
     * Formats the way that the task is to be saved.
     * @return the formatted task.
     */
    public abstract String saveFormat();

    /**
     * Changes the task from incomplete to completed.
     */
    public void completeTask() {
        taskCompleted = true;
    }

    /**
     * Returns the task description.
     * @return task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }
}
