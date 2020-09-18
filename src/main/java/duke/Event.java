package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class contains the name, completion status and timing of the task.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for a Event object
     *
     * @param description The description of the task;
     * @param at The timing of the task;
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of a Event object to be stored in the storage.
     *
     * @return A String representing the code of the task stored in the storage.
     */
    @Override
    public String encode() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("E | %s | %s | %s", getStatusIcon(), description , dateFormatter.format(at));
    }

    /**
     * Returns a string representation of a Event object.
     *
     * @return A String containing the description and completion status of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}