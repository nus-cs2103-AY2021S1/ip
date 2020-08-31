package duke;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents a task with a LocalDate
 * signifying the time of the event.
 */
public class Event extends Task {
    private LocalDate time;

    /**
     * Constructor for Event, initialises the description and time
     * of event.
     * @param description description of the task.
     * @param time the time the event starts.
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        String month = time.getMonth().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"));
        int day = time.getDayOfMonth();
        int year = time.getYear();
        String timeDisplay = String.format("%d %s %d", day, month, year);
        return "[E]" + super.toString() + String.format(" (at: %s)", timeDisplay);
    }
}
