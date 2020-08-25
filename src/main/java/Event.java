import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String date;
    private LocalDate localDate;

    public Event(String task, String date) {
        super(task);
        this.date = date;
        localDate = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
