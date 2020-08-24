import java.util.Date;

public class Event extends Task {
    protected Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toSaveFormat() {
        return String.format("E | %d | %s | %s", this.isDone ? 1 : 0, 
                this.description, DateFormatter.formatSave(this.at)
        );
    }
    
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), DateFormatter.formatDisplay(this.at));
    }
}
