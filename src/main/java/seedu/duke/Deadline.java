package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate localDate;
    protected String due;

    public Deadline(String description, String due) {
        super(description);
        LocalDate d = LocalDate.parse(due);
        this.localDate = d;
        String convertedDate = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.due = convertedDate;
    }

    public Deadline(String task, boolean isDone, String due) {
        super(task, isDone);
        this.due = due;
    }

    public String getFormattedDate() {
        return this.due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.due + ")";
    }
}
