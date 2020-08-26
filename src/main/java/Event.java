import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event is a subtype of Task which requires an attendance at a fixed time.
 * Example:
 *
 *     [E][✘] birthday celebration (at: Mar 7 2020, 08:00 pm)
 *
 * This subtype uses the java.time package for its date and time.
 */

public class Event extends Task {

    /** The date of the event. */
    protected LocalDate date;

    /** The time of the event. */
    protected LocalTime time;

    /**
     * Constructor for Event. The date time is a raw string which will
     * be parsed into LocalDate and LocalTime objects if successful.
     * @param description the description of the task.
     * @param at the raw date time string of the task.
     * @throws DukeException when the date time format is incorrect.
     */
    public Event(String description, String at) throws DukeException {
        super("E", description);
        try {
            String[] dateTime = at.split(" ");
            this.date = parseDate(dateTime[0]);
            this.time = parseTime(dateTime[1]);
        } catch (DateTimeParseException de) {
            throw new DukeException("Invalid date time format!");
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new DukeException("Missing date or time!");
        }
    }

    /**
     * Overloaded constructor for Event.
     * This constructor is usually used when loading tasks from the txt file.
     * @param description the description of the task.
     * @param at the raw date time string of the task.
     * @param isDone the completion flag of the task.
     * @throws DukeException when the date time format is incorrect.
     */
    public Event(String description, String at, boolean isDone) throws DukeException {
        super("E", description, isDone);
        try {
            String[] dateTime = at.split(" ");
            this.date = parseDate(dateTime[0]);
            this.time = parseTime(dateTime[1]);
        } catch (DateTimeParseException de) {
            throw new DukeException("Invalid date time format!");
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new DukeException("Missing date or time!");
        }
    }

    /**
     * Helper method to parse raw date input into LocalDate. The method
     * recognizes special keywords like "today" and "tomorrow" to improve
     * user-friendliness.
     * @param raw the raw date input.
     * @return the LocalDate object representing the date.
     */
    private LocalDate parseDate(String raw) {
        if (raw.equals("today")) {
            return LocalDate.now();
        } else if (raw.equals("tomorrow")) {
            return LocalDate.now().plusDays(1);
        }
        return LocalDate.parse(raw.replaceAll("/", "-"));
    }

    /**
     * Helper method to parse raw time input into LocalTime. The method
     * can provide left padding of zero, if required, and accepts both
     * ":" and "." as separators of hours and minutes.
     * @param raw the raw input time.
     * @return the LocalTime object representing the time.
     */
    private LocalTime parseTime(String raw) {
        String t = String.format("%1$" + 5 + "s",
                raw.replace(".", ":"))
                .replace(" ", "0");
        return LocalTime.parse(t);
    }

    @Override
    public String getDescription() {
        return description + " / " + date + " " + time;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s, %s)",
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                time.format(DateTimeFormatter.ofPattern("hh:mm a")));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Event) {
            Event t = (Event) obj;
            return t.type.equals(this.type) && t.isDone == this.isDone
                    && t.description.equals(this.description)
                    && t.date.equals(this.date) && t.time.equals(this.time);
        }
        return false;
    }
}
