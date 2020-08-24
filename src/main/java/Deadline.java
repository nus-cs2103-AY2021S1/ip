import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        //System.out.println(by);
        this.by = LocalDate.parse(by);
    }
    public Deadline(String description, String by, boolean done) {
        super(description, done);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String inputStyle() {
        return "deadline " + super.inputStyle() + " /by" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
