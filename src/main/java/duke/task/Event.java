package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * An extension of the Task class with an additional field specifying when the event
 * is to happen.
 */
public class Event extends Task {

    /** The date/time of the task or event. */
    protected String at;
    /** The date of the task or event parsed as a LocalDate. */
    protected LocalDate eventDate;

    /**
     * Constructs a new Event object.
     * @param description {@inheritDoc}
     * @param at The date/time of the task or event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            this.eventDate = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            //do nothing
        }
    }

    /**
     * Prints the event date in the format "%B %d %Y", such as JANUARY 1 2000, if available.
     * Otherwise, shows an error message.
     */
    public void printTime() {
        try {
            System.out.println(this.eventDate.getMonth().toString()
                    + " " + this.eventDate.getDayOfMonth()
                    + " " + this.eventDate.getYear());
        } catch (NullPointerException e) {
            System.out.println("No valid date available.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toFileString() {
        int stat;
        if (this.isDone) {
            stat = 1;
        } else {
            stat = 0;
        }
        return String.format("%s | %d | %s at %s", this.getType(), stat,
                this.description, this.at);
    }
}
