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
        String[] splitTag = super.getTxtFormat().split("#", 2);
        if (splitTag.length > 1) {
            return "event, " + splitTag[0] + "/at" + this.dateTime.format(Deadline.INPUT_FORMAT)
                    + " #" + splitTag[1];
        }
        return "event, " + splitTag[0] + "/at" + this.dateTime.format(Deadline.INPUT_FORMAT);
    }

    /**
     * Returns a string representation of object.
     *
     * @return String object.
     */
    @Override
    public String toString() {
        String[] splitTag = super.toString().split("#", 2);
        if (splitTag.length > 1) {
            return "[E]" + splitTag[0] + " (at: " + super.getDateTimeString() + ")"
                    + " #" + splitTag[1];
        }
        return "[E]" + splitTag[0] + " (at: " + super.getDateTimeString() + ")";
    }
}
