package duke.task;

import duke.util.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The fixed duration task with date time provides an additional information on
 * the starting date time of the fixed duration task. Example:
 *     [F][✘] meditate (for: 2 hr) (at: 15 Sep, 11:00 pm)
 */
public class FixedDurationTaskWithDateTime extends FixedDurationTask {

    private LocalDate date;
    private LocalTime time;

    /**
     * Constructs the fixed duration task with date time.
     * @param description the description of the task.
     * @param durationSeconds the duration in seconds.
     * @param dateTime the raw string of the date time.
     * @throws DukeException when task creation fails.
     */
    public FixedDurationTaskWithDateTime(String description, long durationSeconds, String dateTime) throws DukeException {
        super(description, durationSeconds);
        parseDateTime(dateTime);
    }

    /**
     * Gets the date of the task.
     * @return the date of the task.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the time of the task.
     * @return the time of the task.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Parses the raw date time string and sets the task's date and time.
     * @param dateTime the user input containing the date and time.
     */
    public void parseDateTime(String dateTime) throws DukeException {
        if (dateTime.equals("now")) {
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            setStartDateTime(date, time);
            return;
        }
        try {
            String[] dateTimeSplit = dateTime.split("\\s+");
            LocalDate date = parseDate(dateTimeSplit[0]);
            LocalTime time = parseTime(dateTimeSplit[1]);
            setStartDateTime(date, time);
        } catch (DateTimeParseException de) {
            throw new DukeException("Invalid date or time format!");
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new DukeException("Missing date or time!");
        }
    }

    private void setStartDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    private LocalDate parseDate(String raw) {
        if (raw.equals("today")) {
            return LocalDate.now();
        } else if (raw.equals("tomorrow")) {
            return LocalDate.now().plusDays(1);
        }
        return LocalDate.parse(raw.replaceAll("/", "-"));
    }

    private LocalTime parseTime(String raw) {
        String timeString = String.format("%1$" + 5 + "s",
                raw.replace(" ", "0"));
        return LocalTime.parse(timeString);
    }

    /**
     * Gets the description of the task.
     * @return description of the task.
     */
    @Override
    public String getDescription() {
        return String.format("%s / %s / %s %s", description, duration.getSeconds(),
                date.toString(), time.toString());
    }

    /**
     * Formats the string representation of the task and returns it.
     * @return the formatted string representation of the task.
     */
    @Override
    public String toString() {
        String datePattern = (date.getYear() == LocalDate.now().getYear() ? "d MMM" : "d MMM yy");
        String dateTime = String.format(" (at: %s, %s)",
                date.format(DateTimeFormatter.ofPattern(datePattern)),
                time.format(DateTimeFormatter.ofPattern("h:mm a")));
        return super.toString() + dateTime;
    }
}
