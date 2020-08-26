import java.util.Date;

public class Event extends Task {
    protected Date at;


    public Event(String name, Date at) {
        super(name);
        this.at = at;
    }

    @Override
    public Date getDate() {
        return this.at;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), DateFormat.formatDate(this.at));
    }
}
