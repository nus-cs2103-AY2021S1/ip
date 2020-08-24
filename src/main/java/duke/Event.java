package duke;

/**
 * Represents a event containing a description.
 * Inherits from abstract class TimedTask.
 */
public class Event extends TimedTask {

    /**
     * Create a new Event instance.
     *
     * @param description describes the event.
     * @param datetime contains date and time info.
     */
    public Event(String description, String datetime) {
        super(description, datetime);
    }

    /**
     * Returns a text representation of object.
     * This is for purpose of storage in .txt file.
     *
     * @return String of .txt format
     */
    @Override
    protected String getTxtFormat() {
        return "event, " + super.getTxtFormat() + "/at" + this.dateTime.format(Event.inputFormatter);
    }

    /**
     * Returns a string representation of object.
     *
     * @return String object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + super.getDateTimeString() + ")";
    }
}
