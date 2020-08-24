import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    private String parseDateTime(String by) {
        LocalDate d = LocalDate.parse(by);
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parseDateTime(by) + ")";
    }

    @Override
    public String toFileString() {
        return "D\n" + super.getDone() + "\n" + super.toFileString() + "\n" + by + "\n\n";
    }
}