package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * Returns a string that represents the Event task with deadline in the format of MMM dd yyyy.
     *
     * @return String of Event task.
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
