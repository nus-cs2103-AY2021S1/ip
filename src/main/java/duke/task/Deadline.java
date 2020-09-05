package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.InvalidCommandException;
import duke.command.SnoozeCommand;
import duke.component.Parser;
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
     * @param fullDescription the full line of the task
     * @throws InvalidCommandException if the resource file format is invalid
     */
    public Deadline(String fullDescription) throws InvalidCommandException {
        super("");
        String[] info = fullDescription.split(" &&& ");
        if (info.length != 3) {
            throw new InvalidCommandException(Parser.INVALID_FILE_EXCEPTION);
        } else {
            try {
                description = info[0];
                byTime = LocalDate.parse(info[1], Parser.DATE_INPUT_FORMAT);
                repeat = Integer.parseInt(info[2]);
            } catch (Exception e) {
                throw new InvalidCommandException(Parser.INVALID_FILE_EXCEPTION);
            }
        }
    }

    @Override
    public boolean isHappeningOn(LocalDate date) {
        return date.isEqual(byTime);
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
        return byTime.isAfter(date);
    }

    @Override
    public boolean isHappeningAfterToday() {
        return isHappeningAfter(LocalDate.now());
    }

    @Override
    public boolean isHappeningBetween(LocalDate date1, LocalDate date2) {
        super.isHappeningBetween(date1, date2);
        return !byTime.isAfter(date2) && !byTime.isBefore(date1);
    }

    @Override
    public boolean willHappenInDays(int n) {
        super.willHappenInDays(n);
        return isHappeningBetween(LocalDate.now(), LocalDate.now().plusDays(n));
    }

    /**
     * Checks whether the task is overdue.
     * @return true if the task is not done and the deadline is before today
     */
    public boolean isOverdue() {
        return !isDone && byTime.isBefore(LocalDate.now());
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
    public String output() {
        return "D" + super.output() + " | By: " + byTime + "\n";
    }

    @Override
    public String toString() {
        String overdue = isOverdue() ? " This is overdue! The deadline has passed!!!" : "";
        return "[D]" + super.toString() + " (by: "
                + byTime.format(Parser.DATE_OUTPUT_FORMAT) + ")" + overdue;
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
