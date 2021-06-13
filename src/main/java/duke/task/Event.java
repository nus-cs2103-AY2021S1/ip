package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent an Event that has additional LocalDateTime field and extends Task class.
 */
public class Event extends Task {
    private LocalDateTime eventDateTime;
    private String at;

    /**
     * Constructor for Event class.
     * @param description
     * @param at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.eventDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
    }

    /**
     * Constructor for Event class that can initialize the value of isDone.
     * @param description
     * @param at
     * @param isDone
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
        this.eventDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
    }

    /**
     * Return the data of this deadline task to be stored in the storage.
     * @return data of this deadline task as a String
     */
    @Override
    public String getData() {
        return "e/" + super.getData() + "/" + this.at;
    }

    /**
     * Return the string representation of event task.
     * @return string representation
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventDateTime.format(DateTimeFormatter
                .ofPattern("MMM d yyyy h:mm a")) + ")";
    }
}
