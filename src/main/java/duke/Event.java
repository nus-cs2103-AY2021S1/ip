package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a subclass of Task that handles all the event tasks by user.
 */

public class Event extends Task {

    private static final DateTimeFormatter MMM_D_YYYY = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected LocalDate at;

    /**
     * Constructor for an event task.
     * @param description
     * @param at the specified time.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * Overloaded constructor for an event task.
     * @param description
     * @param at the specified time.
     * @param isDone is the event completed.
     */
    Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = LocalDate.parse(at);
    }
    /**
     * convert a Task string to a format that is "[task] [done/not done] /at [date]".
     * @return a formatted string to be stored in a file.
     */
    @Override
    public String inputStyle() {
        return "event " + super.inputStyle()
                + " /at " + at + " #" + super.priority;
    }

    @Override
    public String toString() {
        if (super.priority == Priority.HIGH) {
            return "#" + super.priority.toString() + " [E]" + super.toString() + " (at: "
                    + at.format(MMM_D_YYYY) + ")";
        } else {
            return "[E]" + super.toString() + " (at: "
                    + at.format(MMM_D_YYYY) + ")";
        }
    }
}
