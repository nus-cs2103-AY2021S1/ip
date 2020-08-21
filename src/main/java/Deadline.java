import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, boolean done, LocalDate by) {
        super(name, done);
        this.by = by;
    }

    @Override
    public Deadline setDone(boolean b) {
        return new Deadline(this.getName(),true, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
