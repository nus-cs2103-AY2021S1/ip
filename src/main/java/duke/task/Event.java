package duke.task;

/**
 * Represents a Deadline object which inherits from Task.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructs an instance of Event object.
     * @param description Description of event.
     * @param at Location of event.
     * @param isDone Whether event is done or not.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Implements method specified by abstract class Task.
     * @return String of Event object in format for saving to and retrieving from hard disk.
     */
    public String getParsedTask() {
        return "event " + this.description + " /at " + this.at + System.lineSeparator()
                + this.isDone + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    /**
     * Overrides Object and Task equals method.
     * @param other Object compared to.
     * @return True if other is also an Event object with the same description, at and isDone fields. False otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return this.description.equals(otherEvent.description) &&
                    this.isDone == otherEvent.isDone &&
                    this.at.equals(otherEvent.at);
        } else {
            return false;
        }
    }
}
