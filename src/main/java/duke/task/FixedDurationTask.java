package duke.task;

import duke.util.DukeException;

import java.time.Duration;

/**
 * The fixed duration task is a subtype of a task which has no set date time
 * but has a known duration. Example:
 *     [F][✘] meditate (for: 2 hr)
 */
public class FixedDurationTask extends Task {

    protected final Duration duration;

    /**
     * Constructs the fixed duration task.
     * @param description the description of the task.
     * @param duration the raw string of the duration.
     * @throws DukeException when task creation fails.
     */
    public FixedDurationTask(String description, String duration) throws DukeException {
        super("F", description);
        this.duration = parseDuration(duration);
    }

    /**
     * Constructs the fixed duration task.
     * @param description the description of the task.
     * @param durationSeconds the duration in seconds.
     */
    public FixedDurationTask(String description, long durationSeconds) {
        super("F", description);
        this.duration = Duration.ofSeconds(durationSeconds);
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
    public Duration parseDuration(String raw) throws DukeException {
        if (!raw.contains("h") && !raw.contains("m")) {
            throw new DukeException("Invalid duration!");
        }
        String[] temp = raw.toLowerCase().split(" ");
        int hour = 0;
        int minute = 0;
        try {
            for (String token : temp) {
                if (token.endsWith("h") && hour == 0) {
                    hour = Integer.parseInt(token.split("h")[0]);
                } else if (token.endsWith("m") && minute == 0) {
                    minute = Integer.parseInt(token.split("m")[0]);
                } else if (hour != 0 && minute != 0){
                    break;
                }
            }
        } catch (NumberFormatException nfe) {
            throw new DukeException("Invalid duration!");
        }
        if (hour < 0 || minute < 0 || (hour == 0 && minute == 0)) {
            throw new DukeException("Invalid duration!");
        }
        return Duration.ofMinutes((long) (60 * hour) + minute);
    }

    /**
     * Gets the description of the task.
     * @return description of the task.
     */
    @Override
    public String getDescription() {
        return String.format("%s / %s", description, duration.getSeconds());
    }

    /**
     * Formats the string representation of the task and returns it.
     * @return the formatted string representation of the task.
     */
    @Override
    public String toString() {
        long sec = duration.getSeconds();
        long hr = sec / 3600;
        long min = (sec % 3600) / 60;
        String durationString = " (for: ";
        if (hr != 0) {
            durationString += String.format("%d hr", hr);
        }
        if (min != 0) {
            durationString += String.format(" %d min", min);
        }
        durationString += ")";
        return super.toString() + durationString;
    }
}
