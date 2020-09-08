import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startDate;

    public Event(String in, LocalDateTime start) {
        super(in);
        this.startDate = start;
    }

    public String toString() {
        String doneStatus;
        if (isDone) {
            doneStatus = "✓";
        } else {
            doneStatus = "✗";
        }
        return "[E] [" + doneStatus + "] " + task + " (at: " + dateToString(startDate) + ")";
    }

    public String saveText() {
        return "E | " + super.saveText() + " | " + dateToSave(startDate);
    }
}
