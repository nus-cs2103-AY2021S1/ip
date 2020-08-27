import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    Date timeAt;

    // TODO: 17/8/20 make a toString 
    public Event(String desc, String timeAt) throws ParseException {
        super(desc);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.timeAt = format.parse(timeAt);
    }

    @Override
    public String toString() {
        String sign = done ? "✓" : "✗";
        return "[E][" + sign + "] " + description + " (at:" + timeAt + ")";
    }
}