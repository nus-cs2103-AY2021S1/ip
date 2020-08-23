package duke.task;

import duke.command.InvalidCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate byTime;

    /**
     * Creates a deadline task.
     * @param description the description of the task
     * @param byTime the string description of the time the deadline task should be done by
     * @throws InvalidCommandException if the input time format is not yyyy-MM-dd
     */
    public Deadline(String description, String byTime) throws InvalidCommandException {
        super(description);
        try {
            this.byTime = LocalDate.parse(byTime,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid input date, please input as yyyy-mm-dd.");
        }
    }

    @Override
    public boolean happenOnDate(LocalDate date) {
        return date.isEqual(byTime);
    }

    @Override
    public boolean happenToday() {
        return happenOnDate(LocalDate.now());
    }

    @Override
    public boolean happenBeforeDate(LocalDate date) {
        return byTime.isBefore(date);
    }

    @Override
    public boolean happenBeforeToday() {
        return happenBeforeDate(LocalDate.now());
    }

    @Override
    public boolean happenAfterDate(LocalDate date) {
        return byTime.isAfter(date);
    }

    @Override
    public boolean happenAfterToday() {
        return happenAfterDate(LocalDate.now());
    }

    @Override
    public boolean happenBetween(LocalDate date1, LocalDate date2) {
        super.happenBetween(date1, date2);
        return !byTime.isAfter(date2) && !byTime.isBefore(date1);
    }

    @Override
    public boolean happenIn(int n) {
        super.happenIn(n);
        return happenBetween(LocalDate.now(), LocalDate.now().plusDays(n));
    }

    /**
     * Checks whether the task is overdue.
     * @return true if the task is not done and the deadline is before today
     */
    public boolean isOverdue() {
        return !isDone && byTime.isBefore(LocalDate.now());
    }

    @Override
    public String output() {
        return "D" + super.output() + " | By: " + byTime + "\n";
    }

    @Override
    public String toString() {
        String overdue = isOverdue() ? " This is overdue! The deadline has passed!!!" : "";
        return "[D]" + super.toString() + " (by: " +
                byTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" + overdue;
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
