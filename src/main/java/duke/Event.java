package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that takes place on a date.
 */
public class Event extends Task {

    private LocalDate date;
    private final String ICON = "[E]";
    private final DateTimeFormatter FORMATOFDATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Class constructor.
     * 
     * @param name Name of event.
     * @param date Date of event.
     */
    public Event(String name, String date) {
        super(name);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return ICON + super.getStatusIcon() + " " + super.getTaskName() + " (at: " +
                date.format(FORMATOFDATE) + ")";
    }
}
