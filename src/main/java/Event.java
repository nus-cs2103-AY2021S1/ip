<<<<<<< HEAD
=======
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event task that inherits from Task class, and has an additional condition, which is when it is held at.
 */
>>>>>>> branch-Level-8
public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

<<<<<<< HEAD
=======
    /**
     * Creates a new Event object
     *
     * @param description details about the Event
<<<<<<< HEAD
     * @param isDone whether Event is done or not
     * @param at time/date the event is held at
     * @return Event with a corresponding description and completed status.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
=======
     * @param at date the event is held at in yyyy-mm-dd format
     * @return Event with a corresponding description and sets it as uncompleted.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at.trim());
>>>>>>> branch-Level-8
    }

    /**
     * Overrides toString method of Task class
     *
     * @return Custom description of the event
     */
>>>>>>> branch-Level-7
    @Override
    public String toString() {
<<<<<<< HEAD
        return "[E] " + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns task description and its isDone status for saving.
     *
     * @return string containing its description and its status icon.
     */
    @Override
    public String infoString() {
        String x = "0";
        if (isDone) {
            x = "1";
        }
        return "E | " + x +  " | " + this.description +  " | " + this.at;
=======
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
>>>>>>> branch-Level-8
    }
}
