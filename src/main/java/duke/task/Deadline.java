package duke.task;

import duke.command.InvalidCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate byTime;

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
