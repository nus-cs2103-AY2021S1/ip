// TODO: AddCommand add FixedDurationTask; Parser; StartTaskCommand; HelpCommand Ui

package duke.task;

import duke.util.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * The fixed duration task has two main states; task with duration without a
 * specified start date time, and task with duration and specified start date time.
 * This allows users to create a task without a decided start date time and they
 * are free to provide the start date time in the future.
 */
public class FixedDurationTask extends Task {

    private Optional<LocalDate> startDate;
    private Optional<LocalTime> startTime;
    private final Duration duration;

    public FixedDurationTask(String description, String duration) {
        super("F", description);
        this.duration = parseDuration(duration);
        startDate = Optional.empty();
        startTime = Optional.empty();
    }

    /**
     * Parses the user input string into a duration for the task.
     * This duration object will be stored in the task and used to
     * generate the start time and end time of a particular date once
     * the date time information is provided by the user.
     * Example:
     *     "1h 10m" -> PT1H10M
     *     "2h" -> PT2H
     *     "160m" -> PT2H40M
     * Users have some freedom in choosing whether or not the hour
     * or minute should be included.
     * @param raw the user input to be parsed.
     * @return the duration of the task.
     */
    public Duration parseDuration(String raw) {
        String[] temp = raw.toLowerCase().split(" ");
        int hour;
        int minute;
        if (temp[0].contains("h")) {
            hour = Integer.parseInt(temp[0].split("h")[0]);
            if (temp.length > 1 && temp[1].contains("m")) {
                minute = Integer.parseInt(temp[1].split("m")[0]);
            } else {
                minute = 0;
            }
        } else {
            hour = 0;
            minute = Integer.parseInt(temp[0].split("m")[0]);
        }
        return Duration.ofMinutes((long) (60 * hour) + minute);
    }

    /**
     * Sets the start date and time of the task from the user input.
     * This will mutate the task to provide a start time and end time
     * information whenever it is being displayed.
     * @param dateTime the user input containing the date and time.
     */
    public void setStartDateTimeFromString(String dateTime) throws DukeException {
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

    public void setStartDateTime(LocalDate date, LocalTime time) {
        startDate = Optional.of(date);
        startTime = Optional.of(time);
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

}
