package duke;

/**
 * Implements an Event task
 */
public class Event extends Task {

    protected DukeDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = new DukeDate(at);
    }

    /**
     * String representation of Event object
     * @return String representation of Event object
     */
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * String representation of Event object to be saved to hard disk
     * @return String representation of Event object to be saved to hard disk
     */
    public String fileFormat() {
        return "E , " + super.fileFormat() + at.getStringDate();
    }
}