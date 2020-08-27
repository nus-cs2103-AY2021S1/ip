import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Deadline extends TaskDetail {
    Date timeBy;

    public Deadline(String desc, String timeBy) throws ParseException {
        super(desc);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.timeBy = format.parse(timeBy);
    }

    @Override
    public String toString() {
        String sign = done ? "✓" : "✗";
        return "[D][" + sign + "] " + description + " (by:" + timeBy + ")";
    }
}
