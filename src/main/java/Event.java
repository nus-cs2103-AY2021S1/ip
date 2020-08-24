import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String done, String description, LocalDate at) {
        super(description);
        this.at = at;
        this.isDone = done.equals("1");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    
    @Override
    public String saveString() {
        return "E" + super.saveString() + " , " + this.at;
    }
}
