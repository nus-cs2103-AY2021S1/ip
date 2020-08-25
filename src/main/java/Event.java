import java.time.LocalDateTime;

public class Event extends Task {
    LocalDateTime startDate;
    public Event(String in, LocalDateTime start) {

        super(in);
        this.startDate = start;
    }
    public String toString() {
        String donez;
        if (done) {
            donez = "✓";
        } else {
            donez = "✗";
        }
        return "[E] [" + donez + "] " + task + " (at: " + dateToString(startDate) + ")";
    }

    public String saveText() {
        return "E | " + super.saveText() + " | " + dateToSave(startDate);
    }
}
