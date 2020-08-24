import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = parseDeadline(at);
    }

    private String parseDeadline(String at) {
        LocalDate d = LocalDate.parse(at);
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public String toFileString() {
        return "E\n" + super.getDone() + "\n" + super.toFileString() + "\n" + this.at + "\n\n";
    }
}