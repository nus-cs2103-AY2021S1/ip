import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date date;
    protected boolean isTime;

    public Event (String description, Date date, boolean isTime) {
        super(description);
        this.date = date;
        this.isTime = isTime;
    }

    @Override
    public String toString() {
        String s = isTime ? "MMM dd yyyy hh:mma" : "MMM dd yyyy";
        return "[E]" + super.toString() + " (at: " + new SimpleDateFormat(s).format(date) + ")";
    }
}