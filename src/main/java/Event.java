import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Event is a subtype of Task which requires an attendance at a fixed time.
 * Example:
 *
 *     [E][✘] birthday celebration (at: Mar 7 2020, 08:00 pm)
 *
 * This subtype uses the java.time package for its date and time.
 * The time is optional, as depicted by the use of Optional class, as some
 * tasks do not have exact stipulated time.
 */

public class Event extends Task {

    /** The date of the event. */
    protected LocalDate date;

    /** The time of the event. */
    protected Optional<LocalTime> time;

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
            if (dateTime.length < 2) {
                this.time = Optional.empty();
            } else {
                this.time = Optional.of(parseTime(dateTime[1]));
            }
        } catch (DateTimeParseException de) {
            throw new DukeException("Invalid date or time format!");
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new DukeException("Missing date!");
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
            if (dateTime.length < 2) {
                this.time = Optional.empty();
            } else {
                this.time = Optional.of(parseTime(dateTime[1]));
            }
        } catch (DateTimeParseException de) {
            throw new DukeException("Invalid date or time format!");
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new DukeException("Missing date!");
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
        return String.format("%s / %s%s", description, date,
                time.map(localTime -> " " + localTime).orElse(""));
    }

    @Override
    public String toString() {
        String dt = String.format(" (at: %s",
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        if (time.isPresent()) {
            dt += String.format(", %s)",
                    time.get().format(DateTimeFormatter.ofPattern("hh:mm a")));
        } else {
            dt += ")";
        }
        return super.toString() + dt;
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
