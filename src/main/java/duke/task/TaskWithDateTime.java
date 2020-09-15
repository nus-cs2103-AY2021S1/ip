package duke.task;

import duke.util.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Task with date time encapsulates tasks that contain date time information.
 * Date is compulsory while time is optional. The reasoning is that some tasks
 * can take arbitrary time range or the entire day, hence having the date suffices.
 */
public abstract class TaskWithDateTime extends Task {

    protected LocalDate date;
    protected Optional<LocalTime> time;

    /**
     * Constructs the task with date time.
     * @param description the description of the task.
     * @param dateTime the raw date time string of the task.
     * @throws DukeException when task creation fails.
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
     * Constructs the task with date time with completion status.
     * @param description the description of the task.
     * @param dateTime the raw date time string of the task.
     * @param isDone the completion status of the task.
     * @throws DukeException when task creation fails.
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
        if (!raw.contains("!") || !raw.contains(".")) {
            raw += ":00";
        }
        String timeString = String.format("%1$" + 5 + "s",
                raw.replace(".", ":"))
                .replace(" ", "0");
        return LocalTime.parse(timeString);
    }

    /**
     * Gets the date of the task.
     * @return the date of the task.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the optional time of the task.
     * @return the optional time of the task.
     */
    public Optional<LocalTime> getTime() {
        return time;
    }

    /**
     * Formats the string representation of the task and returns it.
     * @return the formatted string representation of the task.
     */
    @Override
    public String getDescription() {
        return String.format("%s / %s%s", description, date,
                time.map(localTime -> " " + localTime).orElse(""));
    }
}
