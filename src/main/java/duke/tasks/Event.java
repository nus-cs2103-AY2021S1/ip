package duke.tasks;

/**
 * Represents a task with a set time-frame.
 */
public class Event extends TimedTask {

    /**
     * Class constructor.
     *
     * @param description A string representing the task description.
     * @param at          A string representing the task event date/time.
     */
    public Event(String description, String at) {
        super(description, TaskType.EVENT, at);
    }

    /**
     * Class constructor specifying whether or not the task has been completed.
     *
     * @param description A string representing the task description.
     * @param isDone      <code>true</code> if the task has been completed;
     *                    <code>false</code> otherwise.
     * @param at          A string representing the task event date/time.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone, TaskType.EVENT, at);
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), printTime());
    }

}
