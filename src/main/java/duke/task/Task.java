package duke.task;

import java.time.LocalDate;

/**
 * Implements task objects.
 *
 * @author Audrey Felicio Anwar
 */
public class Task {
    protected static final String TICK = "\u2713";
    protected static final String CROSS = "\u2718";
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a task object.
     *
     * @param description The task description.
     * @param done Indicates whether the task is done.
     */
    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    /**
     * Completes a task.
     */
    public void completeTask() {
        isDone = true;
    }

    /**
     * Describes task.
     *
     * @return String that describes task.
     */
    @Override
    public String toString() {
        String symbol = (this.isDone ? String.format("[%s] ", TICK) : String.format("[%s] ", CROSS));
        return symbol + description;
    }

    /**
     * Describes task to be saved in hard disk.
     *
     * @return String that will be stored on hard disk.
     */
    public String saveToHardDisk() {
        int isDone = this.isDone ? 1 : 0;
        return " | " + isDone + " | " + description;
    }

    /**
     * Returns description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Changes task description.
     *
     * @param description Description to be saved.
     */
    public void changeDescription(String description) {
        this.description = description;
    }

    /**
     * Changes task progress mark.
     *
     * @param isDone Progress indicator to be saved.
     */
    public void changeIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Changes time description.
     *
     * @param time Time to be saved.
     */
    public void changeTime(LocalDate time) {}
}
