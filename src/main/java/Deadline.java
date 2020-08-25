package duke;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toDisk() {
        return String.format("deadline\n%s\n%d\n%s", desc, (done == true ? 1 : 0), by);
    }
}