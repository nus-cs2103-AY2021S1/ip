package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event Item in the TaskList.
 */
public class Event extends Task {

    protected LocalDate date;
    protected String time;

    /**
     * Creates a new Event item.
     * @param description of Event.
     * @param date of Event.
     * @param time of Event.
     */
    public Event(String description, LocalDate date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns a {@code String} to be written into saved data.
     */
    @Override
    public String storageForm() {
        return "E" + ", "  + (isDone ? "1" : "0") + ", " + description + ", " + date.toString() + " " + time;
    }

    @Override
    public String getType() { return "Event"; }

    @Override
    public LocalDate getDate() { return this.date; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            + " " + time + ")";
    }
}
