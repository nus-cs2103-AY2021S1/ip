import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date date;
    protected boolean isTime;

    public Deadline (String description, Date date, boolean isTime) {
        super(description);
        this.date = date;
        this.isTime = isTime;
    }

    @Override
    public String toString() {
        String s = isTime ? "MMM dd yyyy hh:mma" : "MMM dd yyyy";
        return "[D]" + super.toString() + " (by: " + new SimpleDateFormat(s).format(date) + ")";
    }
}
