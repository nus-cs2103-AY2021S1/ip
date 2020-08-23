import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String eventDate;
    private LocalDate eventLocalDate;
    private String formatEventDate;

    public Event(String taskName, String eventDate) throws DukeException {
        super(taskName, "E");
        try {
            this.eventDate = eventDate;
            this.eventLocalDate = LocalDate.parse(eventDate);
            this.formatEventDate = this.eventLocalDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (Exception e) {
            throw new DukeException("Please use YYYY-MM-DD format for dates~");
        }
    }
    
    public String getEventDate() {
        return this.eventDate;
    }

    @Override
    public String toString() {
        String check;
        if (done == true) {
            check = "✓";
        } else {
            check = "✗";
        }
        return "[" + taskType + "][" + check + "] " + taskName + " (at:" + formatEventDate + ")";
    }
}
