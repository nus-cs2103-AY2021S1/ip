package mattbot.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task that will be created by the user to be added into the list.
 */
public class task {
    protected static int count = 0;
    protected boolean completed;
    protected String name;
    protected LocalDateTime date;
    protected String type;

    /**
     * Creates a task object with the set if information entered by the user.
     *
     * @param name the name of the task.
     * @param type the type of task to be created.
     */
    public task(String name, String type) {
        this.count++;
        this.name = name;
        this.completed = false;
        this.type = type;
    }

    /**
     * Returns the name of the task.
     *
     * @return String name of the task.
     */
    public String getTaskName() {
        return this.name;
    }

    /**
     * Returns whether the task is completed or not.
     *
     * @return boolean completion.
     */
    public boolean getTaskCompleted() {
        return this.completed;
    }

    /**
     * Sets the task to be completed.
     */
    public void setDone() {
        this.completed = true;
    }

    /**
     * Returns the type of the task.
     *
     * @return String type of task.
     */
    public String getTaskType() {
        return this.type;
    }

    /**
     * Returns the date and time of the task to be completed by.
     *
     * @return LocalDateTime date and time.
     */
    public LocalDateTime getTaskDate() {
        return this.date;
    }
    /**
     * Sets the date and time of the task.
     *
     * @param date date and time of the task.
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }


}
