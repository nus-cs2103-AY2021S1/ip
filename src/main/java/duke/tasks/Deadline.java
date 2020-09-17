package duke.tasks;

/**
 * Represents a task with a set deadline.
 */
public class Deadline extends TimedTask {

    /**
     * Class constructor.
     *
     * @param description A string representing the task description.
     * @param by          A string representing the task deadline date/time.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE, by);
    }

    /**
     * Class constructor specifying whether or not the task has been completed.
     *
     * @param description A string representing the task description.
     * @param isDone      <code>true</code> if the task has been completed;
     *                    <code>false</code> otherwise.
     * @param by          A string representing the task deadline date/time.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone, TaskType.DEADLINE, by);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), printTime());
    }

}
