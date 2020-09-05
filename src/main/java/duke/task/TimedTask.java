package duke.task;

import duke.command.InvalidCommandException;

public abstract class TimedTask extends Task {
    public TimedTask(String description) {
        super(description);
    }

    /**
     * Snoozes the task to a designated new time.
     * @param input the input command split by space
     * @return the output notice string of the snooze command
     * @throws InvalidCommandException if the input format, or the task index and the time is invalid
     */
    abstract public String snoozeTo(String[] input) throws InvalidCommandException;
}
