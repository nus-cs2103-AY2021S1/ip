package duke.task;

import duke.command.InvalidCommandException;

public abstract class TimedTask extends Task {
    protected int repeat;

    public TimedTask(String description) {
        super(description);
        repeat = 0;
    }

    /**
     * Snoozes the task to a designated new time.
     * @param input the input command split by space
     * @return the output notice string of the snooze command
     * @throws InvalidCommandException if the input format, or the task index and the time is invalid
     */
    abstract public String snoozeTo(String[] input) throws InvalidCommandException;

    /**
     * Sets the task to repeat regularly.
     * @param n the number of days to repeat
     * @return the output notice string of the repeat command
     * @throws InvalidCommandException if the task does not have a fixed time yet
     */
    abstract public String repeat(int n) throws InvalidCommandException;

    protected String repeatMessage() {
        if (repeat == 0) {
            return "";
        } else {
            return String.format(" repeat every %d days", repeat);
        }
    }
}
