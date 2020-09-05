package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Deadline extends Task {

    protected String by;
    protected LocalDate deadlineDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            deadlineDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            //do nothing
        }
    }

    public void printTime() {
        try {
            System.out.println(deadlineDate.getMonth().toString()
                + " " + deadlineDate.getDayOfMonth()
                + " " + deadlineDate.getYear());
        } catch (NullPointerException e) {
            System.out.println("No valid date available.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toFileString() {
        int stat = 0;
        if (isDone) {
            stat = 1;
        }
        return String.format("%s | %d | %s by %s", getType(), stat, description, by);
    }
}
