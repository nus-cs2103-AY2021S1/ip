package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class that represents a event task.
 *
 * @author Zeng Yu Ting
 * @version 1.0
 * @since 2020-15-08
 */
public class Event extends Task {
    private LocalDateTime dueDate;
    public Event(String description, String dueDate) {
        this.description = description;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println(dueDate);
        this.dueDate = LocalDateTime.parse(dueDate, dateFormat);
    }

    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + "(at: " + dueDate.format(dateFormat) + ")";
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * This method returns the string to be written for the event.
     */
    public String toWriteString() { return "E " +  super.toWriteString() + " | " + dueDate; }
}
