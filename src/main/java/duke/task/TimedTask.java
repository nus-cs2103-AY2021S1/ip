package duke.task;

import java.time.LocalDate;

import duke.command.InvalidCommandException;
import duke.component.Storage;

public abstract class TimedTask extends Task {
    protected int repeat;
    protected LocalDate lastDone;

    /**
     * Creates a TimedTask with the given description and initialize the repeat period to be 0 days, i.e. not repeat.
     * @param description
     */
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
    public abstract String snoozeTo(String[] input) throws InvalidCommandException;

    /**
     * Sets the task to repeat regularly.
     * @param n the number of days to repeat
     * @return the output notice string of the repeat command
     * @throws InvalidCommandException if the task does not have a fixed time yet
     */
    public abstract String repeat(int n) throws InvalidCommandException;

    protected String repeatMessage() {
        if (repeat == 0) {
            return "";
        } else {
            return String.format(" repeat every %d days", repeat);
        }
    }

    protected String lastDoneMessage() {
        if (isDone) {
            return Storage.SPLITTER + lastDone;
        } else {
            return "";
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

    protected void markAsDone(LocalDate initial) throws InvalidCommandException {
        if (repeat == 0) {
            super.markAsDone();
            lastDone = LocalDate.now();
        } else if (!isDone) {
            isDone = true;
            lastDone = initial;
        } else {
            lastDone = lastDone.plusDays(repeat);
        }
    }
}
