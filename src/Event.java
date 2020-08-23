import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, LocalDate at) {
        super(description);
        this.isDone = isDone;
        this.at = at;
    }

    public String saveText(){
        return "E | " + getStatusIcon() + " | " + description + " | " + at + "\n";
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
