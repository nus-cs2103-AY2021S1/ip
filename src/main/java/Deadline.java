import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task implements Serializable {

    protected String by;
    LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.date = LocalDate.parse(by);
        this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by:" + by + ")";
    }
}
