package duke.task;

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
}
