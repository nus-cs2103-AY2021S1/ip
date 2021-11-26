package duke;

/**
 * The Event class represents tasks that have a duration period.
 */
public class Event extends Task {

    /**
     * Constructor which takes in a description of the task name and stores a duration period.
     *
     * @param description name of the task
     * @param by period of the event duration
     */
    public Event(String description, String by) {
        super(description);
        symbol = "[E]";
        this.by = by;
    }

    @Override
    public String toString() {
        return (getSymbol() + super.toString() + " (at: " + getDate() + ")");
    }
}
