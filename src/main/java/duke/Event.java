package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a subclass of Task that handles all the event tasks by user.
 */

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    Event(String description, String at, boolean done) {
        super(description, done);
        this.at = LocalDate.parse(at);
    }
    /**
     * convert a Task string to a format that is "[task] [done/not done] /at [date]"
     * @return a formatted string to be stored in a file
     */
    @Override
    public String inputStyle() {
        return "event " + super.inputStyle() + " /at " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
