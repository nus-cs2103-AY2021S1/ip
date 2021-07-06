package duke.task;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents a task.
 */
public class Task {

    public static final String TICK_ICON = "\u2713";
    public static final String CROSS_ICON = "\u2718";

    /** Description of a task */
    protected String desciption;

    /** The status of the task*/
    protected boolean isDone;

    /** Time for certain task*/
    protected Optional<LocalDateTime> time;

    /**
     * Creates a task with description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.desciption = description;
        this.isDone = false;
    }

    /**
     * Returns the icon of current task status.
     *
     * @return Tick if task is done and cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? Task.TICK_ICON : Task.CROSS_ICON);
    }

    /**
     * Marks current instance of task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns true when the description contains certain keyword, false otherwise.
     *
     * @param keyword Search key.
     * @return True when contains the search key and false otherwise.
     */
    public boolean containsKeyWord(String keyword) {
        return this.desciption.contains(keyword);
    }

    /**
     * Returns the status of a task.
     *
     * @return True if the task has done and false other wise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns a string to be stored in data file.
     *
     * @return Formatted string to be used in data file.
     */
    public String toFileStringFormat() {
        return String.format("%d / %s", isDone ? 1 : 0, this.desciption);
    }

    /**
     * Returns whether the task has the exact description as this object.
     *
     * @param task Task that user intend to add.
     * @return True if the description is the same, false otherwise.
     */
    public boolean isExactDescription(Task task) {
        return this.desciption.equals(task.desciption);
    }

    /**
     * Reschedule the task.
     *
     * @param time Target time of the task.
     * @return Task that has been reschedule.
     */
    public Task reschedule(LocalDateTime time) {
        this.time = Optional.of(time);
        return this;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.desciption);
    }

}
