package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents event task of user.
 */
public class Event extends Task {

    /**
     * Creates Event object.
     * @param description Description of the Event object.
     * @param at string representation of time of Event object.
     */
    public Event(String description, String at) {
        super(description);
        this.date = LocalDate.parse(at);
        this.dateString = at;
    }

    /**
     * Returns string representation of Event object to be stored in file.
     * @return string representation of Event object for file storage.
     */
    @Override
    public String convertToFileString() {
        String doneInteger = isDone ? "1" : "0";
        return "E | " + doneInteger + " | " + this.description + " | " + this.dateString;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
