package duke.model;

/**
 * Represents a Duration Task.
 */
public class DurationTask extends Task {
    private int hours;

    /**
     * Constructs a duration task.
     * @param description is the description of a duration task.
     */
    public DurationTask(String description, int hours) {
        super(description);
        this.hours = hours;
    }

    /**
     * Constructs a duration task.
     * @param description is the description of a duration task.
     * @param hours is the duration (in hours).
     * @param isDone whether the task in done or not.
     */
    public DurationTask(String description, int hours, boolean isDone) {
        super(description);
        this.hours = hours;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "[U]" + super.toString() + " (" + this.hours + " hours)";
    }
}
