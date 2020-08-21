import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
