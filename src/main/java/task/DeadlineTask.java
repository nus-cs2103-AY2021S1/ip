package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected String by;
    protected LocalDate date;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
        this.date = null;
    }

    public DeadlineTask(String description, String by, LocalDate date) {
        super(description);
        this.by = by;
        this.date = date;
    }

    public DeadlineTask(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        if (by.contains(")")) {
            by = by.substring(0, by.indexOf(')'));
        }

        if (date == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter
                    .ofPattern("MMM d yyyy")) + ")";
        }

    }
}
