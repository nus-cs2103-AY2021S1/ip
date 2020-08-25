import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }
    
    @Override
    public Event markAsDone() {
        return new Event(this.description, true, this.at);
    }

    @Override
    public String stringify() {
        String number = isDone ? "1" : "0";
        return "E | " + number + " | " + super.description + " | " + this.at;
    }
    
    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (at: " + at + ")";
    }
}
