import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Events extends Task {
    protected String by;
    LocalDate date;

    public Events(String description, String by) {
        super(description);
        this.date = LocalDate.parse(by);
        this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at:" + by + ")";
    }
}
