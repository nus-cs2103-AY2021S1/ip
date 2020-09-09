package pandabot.tasks;

import java.time.format.DateTimeParseException;

import pandabot.exceptions.PandaBotException;
import pandabot.exceptions.PandaBotInsufficientArgumentException;

/**
 * Represents a TimedTask, which is an abstract class that can be used to
 * represent tasks which are time-based.
 */
public abstract class TimedTask extends Task {
    protected String time;

    /**
     * Creates a TimedTask object with the given description and time.
     * It can accept both formatted and unformatted times.
     * Formatted time have to be in the format: dd/MM/yyyy HHmm
     *
     * @param description the given task description
     * @param inputTime the given task time
     * @throws PandaBotException if description or inputTime is empty
     */
    public TimedTask(String description, String inputTime) throws PandaBotException {
        super(description);

        String time = inputTime.strip();

        if (time.length() == 0) {
            throw new PandaBotInsufficientArgumentException();
        }

        // check if a formatted date and time is given
        try {
            DateAndTime dateTime = new DateAndTime(time);
            this.time = dateTime.toString();
        } catch (DateTimeParseException e) {
            // if the input couldn't be parsed, the input is an unformatted dueBy
            this.time = inputTime;
        }
    }
}
