package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.InvalidCommandException;
import duke.command.SnoozeCommand;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.Ui;

/**
 * Represents a deadline task that consists of a description and a date as the deadline for completing the task.
 */
public class Deadline extends TimedTask {
    private LocalDate byTime;

    /**
     * Creates a deadline task.
     * @param description the description of the task
     * @param byTime the string description of the time the deadline task should be done by
     * @throws InvalidCommandException if the input time format is not yyyy-MM-dd
     */
    public Deadline(String description, String byTime) throws InvalidCommandException {
        super(description);
        try {
            this.byTime = LocalDate.parse(byTime, Parser.DATE_INPUT_FORMAT);
        } catch (Exception e) {
            throw new InvalidCommandException(Parser.INVALID_DATE_FORMAT_EXCEPTION);
        }
    }

    /**
     * Creates a deadline task using the resource file.
     * @param taskInfo the full line of the task
     * @throws InvalidCommandException if the resource file format is invalid
     */
    public Deadline(String[] taskInfo) throws InvalidCommandException {
        super("");
        assert taskInfo[0].equals("D") : "Wrong read of file";
        try {
            int done = Integer.parseInt(taskInfo[1]);
            if ((done == 0 && taskInfo.length != 5) || (done == 1 && taskInfo.length != 6)) {
                throw new InvalidCommandException(Parser.INVALID_FILE_EXCEPTION);
            }
            try {
                description = taskInfo[2];
                byTime = LocalDate.parse(taskInfo[3], Parser.DATE_INPUT_FORMAT);
                repeat = Integer.parseInt(taskInfo[4]);
                if (done == 1) {
                    lastDone = LocalDate.parse(taskInfo[5], Parser.DATE_INPUT_FORMAT);
                    this.isDone = true;
                }
            } catch (Exception e) {
                throw new InvalidCommandException(Parser.INVALID_FILE_EXCEPTION);
            }
        } catch (StackOverflowError | NumberFormatException e) {
            throw new InvalidCommandException(Parser.INVALID_FILE_EXCEPTION);
        }
    }

    @Override
    public boolean isHappeningOn(LocalDate date) {
        return isHappeningOn(date, byTime);
    }

    @Override
    public boolean isHappeningToday() {
        return isHappeningOn(LocalDate.now());
    }

    @Override
    public boolean hasHappenedBefore(LocalDate date) {
        return byTime.isBefore(date);
    }

    @Override
    public boolean hasHappenedBeforeToday() {
        return hasHappenedBefore(LocalDate.now());
    }

    @Override
    public boolean isHappeningAfter(LocalDate date) {
        return repeat > 0 || byTime.isAfter(date);
    }

    @Override
    public boolean isHappeningAfterToday() {
        return isHappeningAfter(LocalDate.now());
    }

    @Override
    public boolean isHappeningBetween(LocalDate date1, LocalDate date2) {
        return isHappeningBetween(date1, date2, byTime);
    }

    @Override
    public boolean willHappenInDays(int n) {
        return isHappeningBetween(LocalDate.now(), LocalDate.now().plusDays(n));
    }

    @Override
    public String snoozeTo(String[] input) throws InvalidCommandException {
        if (input.length != SnoozeCommand.SNOOZE_DEADLINE_COMMAND_LENGTH) {
            throw new InvalidCommandException(Parser.INVALID_DATE_FORMAT_EXCEPTION);
        }
        String dateTimeStr = input[3];
        try {
            LocalDate newDate = LocalDate.parse(dateTimeStr, Parser.DATE_INPUT_FORMAT);
            if (newDate.isAfter(byTime)) {
                LocalDate originalDate = byTime;
                byTime = newDate;
                return String.format(Ui.SNOOZE_TASK_OUTPUT_FORMAT, this, originalDate, newDate);
            } else {
                throw new InvalidCommandException(Parser.SNOOZE_TO_EARLIER_TIME_EXCEPTION);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException(Parser.INVALID_DATE_FORMAT_EXCEPTION);
        }
    }

    @Override
    public String repeat(int n) {
        repeat = n;
        return String.format(Ui.REPEAT_TASK_OUTPUT_FORMAT, n, this);
    }

    @Override
    public void markAsDone() throws InvalidCommandException {
        markAsDone(byTime);
    }

    @Override
    public String outputToFile() {
        return "D" + super.outputToFile() + Storage.SPLITTER + byTime + Storage.SPLITTER
                + repeat + lastDoneMessage() + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byTime.format(Parser.DATE_OUTPUT_FORMAT) + repeatMessage() + ")";
    }

    /**
     * Checks whether the given object equals this Deadline task.
     * @param obj the given object to compare
     * @return true if the object is a Deadline and both the description and byTime equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Deadline) {
            Deadline o = (Deadline) obj;
            return description.equals(o.description) && byTime.isEqual(o.byTime);
        } else {
            return false;
        }
    }
}
