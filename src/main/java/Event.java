import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate time;

    public Event(String description, boolean done, LocalDate time) {
        super(description, done);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String saveToHardDisk() {
        return "E" + super.saveToHardDisk() + " | " + this.time;
    }
}
