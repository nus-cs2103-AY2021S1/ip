import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime eventDateTime;
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.eventDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
    }
    
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
        this.eventDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
    }

    @Override
    public String getData() {
        return "e/" + super.getData() + "/" + this.at;
    }
  
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")) + ")";
    }
}
