/**
 * Event class is a subclass of Task which holds information of an Event
 * with an occuring time and date.
 */
public class Event extends Task {

    protected DateAndTime at;

    /**
     * Constructs an Event task with the specified description and the timing.
     *
     * @param description the string that describes that the event is
     * @param at          the date and time of the event
     */
    public Event(String description, DateAndTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs an Event with the specified description, timing and the status.
     *
     * @param description the string that describes that the event is
     * @param at          the date and time of the event
     * @param isDone      the boolean to indicate the whether the event is completed or not
     */
    public Event(String description, DateAndTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * The overridden toString() method to print out the correct representation of an event.
     *
     * @return a string representation of an event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
