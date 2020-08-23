import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private Date time;
    private SimpleDateFormat formatter;
    public Event(String desc, Date time) {
        super(desc);
        this.time = time;
        formatter = new SimpleDateFormat ("MMM dd yyyy hh:mm a");
    }


    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), formatter.format(time));
    }

    @Override
    public String toFileString() {
        return "E\n"+super.toFileString()+formatter.format(time) + "\n";
    }
}
