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
}
