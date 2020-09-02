package duke;

/**
 * Deadline class is a subclass of Task which holds information for this type of tasks with
 * a date and time (which is the deadline :) ).
 */
public class Deadline extends Task {

    protected DateAndTime by;

    /**
     * Constructs a Deadline task with a description and a date and time specified.
     *
     * @param description the string that contains the description of a deadline task
     * @param by          the date and time object which specifies the timing
     */
    public Deadline(String description, DateAndTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with a description, a date and time and a specified status
     * done or not done.
     *
     * @param description the string that contains the description of a deadline task
     * @param by          the date and time object which specifies the timing
     * @param isDone      the boolean to indicated if the task is completed or not
     */
    public Deadline(String description, DateAndTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Overridden toString() method to print out a deadline task in the right format.
     *
     * @return a string that represents a deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
