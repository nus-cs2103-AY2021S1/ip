package duke.task;

/**
 * A type of Task that has description and an "at" date
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for creating Event object
     *
     * @param description name of task
     * @param at event date and time
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Alternative constructor that accounts for progress of task
     *
     * @param description name of task
     * @param at event date and time
     * @param isDone whether task is completed
     */
    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    /**
     * toString method for Event
     *
     * @return task in string form
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
