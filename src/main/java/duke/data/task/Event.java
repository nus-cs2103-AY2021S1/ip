package duke.data.task;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents a task with a LocalDate
 * signifying the time of the event.
 */
public class Event extends Task {
    private LocalDate date;

    /**
     * Constructor for Event, initialises the description and time
     * of event.
     * @param description description of the task.
     * @param date the time the event starts.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"));
        int day = date.getDayOfMonth();
        int year = date.getYear();
        String timeDisplay = String.format("%d %s %d", day, month, year);
        return "[E]" + super.toString() + String.format(" (at: %s)", timeDisplay);
    }
}
