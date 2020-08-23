import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends ToDo {
    private Date time;
    private SimpleDateFormat formatter;
    public Deadline(String desc, Date time) {
        super(desc);
        this.time = time;
        formatter = new SimpleDateFormat ("MMM dd yyyy hh:mm a");
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), formatter.format(time));
    }
}
