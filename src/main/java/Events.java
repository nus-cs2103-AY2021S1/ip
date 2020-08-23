import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected LocalDate scheduled;

    public Events(String description, String scheduled) {
        super(description);
        this.scheduled = LocalDate.parse(scheduled);
    }

    public Events(String description, String scheduled, boolean isDone) {
        super(description, isDone);
        this.scheduled = LocalDate.parse(scheduled);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + scheduled.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
