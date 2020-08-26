import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline is a subtype of Task which has a stipulated deadline for the task
 * to be completed by. Example:
 *
 *     [D][✘] assignment (by: Aug 26 2020, 11:59 pm)
 *
 * This subtype uses the java.time package for its date and time.
 */

public class Deadline extends Task {

    /** The date of the task. */
    protected LocalDate date;

    /** The time of the task. */
    protected LocalTime time;

    /**
     * Constructor for Deadline. The date time is a raw string which will
     * be parsed into LocalDate and LocalTime objects if successful.
     * @param description the description of the task.
     * @param by the raw date time string of the task.
     * @throws DukeException when the date time format is incorrect.
     */
    public Deadline(String description, String by) throws DukeException {
        super("D", description);
        try {
            String[] dateTime = by.split(" ");
            this.date = parseDate(dateTime[0]);
            this.time = parseTime(dateTime[1]);
        } catch (DateTimeParseException de) {
            throw new DukeException("Invalid date time format!");
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new DukeException("Missing date or time!");
        }
    }

    /**
     * Overloaded constructor for Deadline.
     * This constructor is usually used when loading tasks from the txt file.
     * @param description the description of the task.
     * @param by the raw date time string of the task.
     * @param isDone the completion flag of the task.
     * @throws DukeException when the date time format is incorrect.
     */
    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super("D", description, isDone);
        try {
            String[] dateTime = by.split(" ");
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
        return LocalDate.parse(raw);
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
        return super.toString() + String.format(" (by: %s, %s)",
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                time.format(DateTimeFormatter.ofPattern("hh:mm a")));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Deadline) {
            Deadline t = (Deadline) obj;
            return t.type.equals(this.type) && t.isDone == this.isDone
                    && t.description.equals(this.description)
                    && t.date.equals(this.date) && t.time.equals(this.time);
        }
        return false;
    }
}
