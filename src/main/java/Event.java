import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate time;

    public Event(boolean isComplete, int index, String instructions, String time) {
        super(isComplete, index, instructions);
        this.time = parseTime(time);
    }

    public Event(boolean isComplete, int index, String instructions, LocalDate time) {
        super(isComplete, index, instructions);
        this.time = time;
    }
    public LocalDate getTime() {
        return this.time;
    }

    public LocalDate parseTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(time, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        if (this.isComplete) {
            return "[E][✓] " + this.instructions + " (at: " + outputFormatter.format(this.time) + ")";
        } else {
            return "[E][✗] " + this.instructions + " (at: " + outputFormatter.format(this.time) + ")";
        }
    }
}
