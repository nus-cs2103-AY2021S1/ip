import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate localDate;
    protected String date;

    public Event(String event, String date) {
        super(event);
        LocalDate d = LocalDate.parse(date);
        this.localDate = d;
        String convertedDate = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.date = convertedDate;
    }

    public Event(String task, boolean isDone, String date) {
        super(task, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}
