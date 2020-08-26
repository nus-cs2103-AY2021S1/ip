import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDate at;

    public Events(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMM uuuu")) + ")";
    }
}