package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected String formattedDate;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
        this.formattedDate = LocalDate.parse(by).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String recordString() {
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
