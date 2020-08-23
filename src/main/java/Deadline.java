import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone,  LocalDate time) {
        super(description, isDone);
        this.by = time;
    }
    
    public LocalDate getByWhen() {
        return this.by;
    }
    
    @Override
    public String toString() {
        String formattedTimeBy = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[D]" + super.toString() + " (by: %s)", formattedTimeBy);
    }

}
