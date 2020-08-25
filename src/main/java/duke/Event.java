package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class represents a task with a specific time
 */
public class Event extends Task {
    private LocalDate time;

    public Event(boolean isComplete, int index, String instructions, String time) {
        super(isComplete, index, instructions);
        this.time = parseTime(time);
    }

    public Event(boolean isComplete, int index, String instructions, LocalDate time) {
        super(isComplete, index, instructions);
        this.time = time;
    }

    /**
     * Returns time of Event
     * @return Time as LocalDate object
     */
    public LocalDate getTime() {
        return this.time;
    }

    /**
     * Converts time in String format to LocalDate format
     * @param time A String represented as "yyyy-MM-d"
     * @return A LocalDate object
     */
    public LocalDate parseTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(time, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        if (this.isComplete) {
            return "[E][✓] " + this.instructions + " (at: " + outputFormatter.format(this.time) + ")";
        } else {
            return "[E][✗] " + this.instructions + " (at: " + outputFormatter.format(this.time) + ")";
        }
    }
}
