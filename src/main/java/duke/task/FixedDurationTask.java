package duke.task;

import duke.util.DukeException;

import java.time.Duration;

/**
 * The fixed duration task has two main states; task with duration without a
 * specified start date time, and task with duration and specified start date time.
 * This allows users to create a task without a decided start date time and they
 * are free to provide the start date time in the future.
 */
public class FixedDurationTask extends Task {

    protected final Duration duration;

    public FixedDurationTask(String description, String duration) throws DukeException {
        super("F", description);
        this.duration = parseDuration(duration);
    }

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

    @Override
    public String getDescription() {
        return String.format("%s / %s", description, duration.getSeconds());
    }

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
