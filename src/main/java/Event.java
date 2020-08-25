import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    private final Date at;

    public Event(String description, Date at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    @Override
    public String getSavedString() {
        return super.getSavedString() + " | " + (new SimpleDateFormat("MMM d yyyy")).format(at);
    }

    @Override
    public boolean isOccuringOn(Date date) {
        return date.equals(at);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + (new SimpleDateFormat("MMM d yyyy")).format(at) + ")";
    }
    
}
