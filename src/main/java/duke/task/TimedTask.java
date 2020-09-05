package duke.task;

import duke.command.InvalidCommandException;

import java.time.LocalDate;

public abstract class TimedTask extends Task {
    protected int repeat;
    protected LocalDate lastDone;

    public TimedTask(String description) {
        super(description);
        repeat = 0;
        lastDone = null;
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

    protected boolean isHappeningOn(LocalDate date, LocalDate taskDate) {
        if (repeat == 0) {
            return date.isEqual(taskDate);
        } else {
            LocalDate datePointer = taskDate;
            boolean found = datePointer.isEqual(date);
            while (!found && datePointer.isBefore(date)) {
                datePointer = datePointer.plusDays(repeat);
                found = datePointer.isEqual(date);
            }
            return found;
        }
    }

    protected boolean isHappeningBetween(LocalDate date1, LocalDate date2, LocalDate taskDate) {
        if (repeat == 0) {
            return !taskDate.isAfter(date2) && !taskDate.isBefore(date1);
        } else if (taskDate.isAfter(date2)) {
            return false;
        } else {
            LocalDate datePointer = taskDate;
            while (datePointer.isBefore(date1)) {
                datePointer = datePointer.plusDays(repeat);
            }
            return datePointer.isBefore(date2);
        }
    }
}
