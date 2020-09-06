package duke.task;

import duke.util.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Some tasks have more information such as the date and time of their
 * occurrence or a particular deadline in which the task has to be attended to or
 * completed by.
 *
 * This subtype uses the java.time package for its date and time.
 * the time is optional, as depicted by the use of Optional class, as some
 * tasks do not have exact stipulated time.
 */
public abstract class TaskWithDateTime extends Task {

    protected LocalDate date;
    protected Optional<LocalTime> time;

    /**
     * Constructor for tasks with date time. The date time is a raw string which will
     * be parsed into LocalDate and LocalTime objects if successful.
     * @param description the description of the task.
     * @param dateTime the raw date time string of the task.
     * @throws DukeException when the date time format is incorrect.
     */
    public TaskWithDateTime(String type, String description, String dateTime) throws DukeException {
        super(type, description);
        try {
            String[] dateTimeSplit = dateTime.split(" ");
            date = parseDate(dateTimeSplit[0]);
            if (dateTimeSplit.length < 2) {
                time = Optional.empty();
            } else {
                time = Optional.of(parseTime(dateTimeSplit[1]));
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
     * @param dateTime the raw date time string of the task.
     * @param isDone the completion flag of the task.
     * @throws DukeException when the date time format is incorrect.
     */
    public TaskWithDateTime(String type, String description, String dateTime, boolean isDone) throws DukeException {
        super(type, description, isDone);
        try {
            String[] dateTimeSplit = dateTime.split(" ");
            date = parseDate(dateTimeSplit[0]);
            if (dateTimeSplit.length < 2) {
                time = Optional.empty();
            } else {
                time = Optional.of(parseTime(dateTimeSplit[1]));
            }
        } catch (DateTimeParseException de) {
            throw new DukeException("Invalid date or time format!");
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new DukeException("Missing date!");
        }
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

    public LocalDate getDate() {
        return date;
    }

    public Optional<LocalTime> getTime() {
        return time;
    }

    @Override
    public String getDescription() {
        return String.format("%s / %s%s", description, date,
                time.map(localTime -> " " + localTime).orElse(""));
    }
}
