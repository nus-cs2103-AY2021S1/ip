package duke.task;

import duke.util.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FixedDurationTaskWithDateTime extends FixedDurationTask {

    private LocalDate date;
    private LocalTime time;

    public FixedDurationTaskWithDateTime(String description, String duration, String dateTime) throws DukeException {
        super(description, duration);
        parseDateTime(dateTime);
    }

    public FixedDurationTaskWithDateTime(String description, long durationSeconds, String dateTime) throws DukeException {
        super(description, durationSeconds);
        parseDateTime(dateTime);
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    /**
     * Sets the start date and time of the task from the user input.
     * This will mutate the task to provide a start time and end time
     * information whenever it is being displayed.
     * @param dateTime the user input containing the date and time.
     */
    public void parseDateTime(String dateTime) throws DukeException {
        try {
            String[] dateTimeSplit = dateTime.split(" ");
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
                raw.replace(".", ":"))
                .replace(" ", "0");
        return LocalTime.parse(timeString);
    }

    @Override
    public String getDescription() {
        return String.format("%s / %s / %s %s", description, duration.getSeconds(),
                date.toString(), time.toString());
    }

    @Override
    public String toString() {
        String datePattern = (date.getYear() == LocalDate.now().getYear() ? "d MMM" : "d MMM yy");
        String dateTime = String.format(" (at: %s, %s)",
                date.format(DateTimeFormatter.ofPattern(datePattern)),
                time.format(DateTimeFormatter.ofPattern("h:mm a")));
        return super.toString() + dateTime;
    }
}
