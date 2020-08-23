package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDate by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getByDate() {
        return this.by;
    }

    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
