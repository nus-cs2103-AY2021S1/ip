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

    public Deadline (String description, Date date, boolean isTime, boolean isDone) {
        super(description, isDone);
        this.date = date;
        this.isTime = isTime;
    }

    public boolean getIsTime() {
        return isTime;
    }

    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String s = isTime ? "MMM dd yyyy hh:mma" : "MMM dd yyyy";
        return "[D]" + super.toString() + " (by: " + new SimpleDateFormat(s).format(date) + ")";
    }

    @Override
    public String toStoredTextString() {
        String s = isTime ? "MMM dd yyyy hh:mma" : "MMM dd yyyy";
        return "D | " + super.toStoredTextString() + " | " + new SimpleDateFormat(s).format(date) + " | " + (isTime ? "1" : "0");
    }
}
