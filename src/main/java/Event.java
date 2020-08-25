import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.date = parseDate(at);
    }

    private LocalDate parseDate(String at) {
        return LocalDate.parse(at);
    }

    private String printDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(dateTimeFormatter);
    }

    @Override
    public String diskFormat() {
        return "     E | " + super.diskFormat() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + printDate() + ")";
    }
}
