package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String done, String description, LocalDate by) {
        super(description);
        this.by = by;
        this.isDone = (done == "1");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String splitToString() {
        return "\n" + "D" + "/" + this.ifDone() + "/" + this.description + "/" + this.by.toString();
    }
}