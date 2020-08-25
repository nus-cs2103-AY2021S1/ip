import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate by;


    public Event(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.getStatusIcon()
                + " "
                + super.toString()
                + "(at: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}