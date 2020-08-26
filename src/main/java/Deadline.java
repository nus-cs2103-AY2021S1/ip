import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String byString;
    protected LocalDate byDate;

    Deadline(String task, String date) {
        super(task);
        this.byString = date;
        this.byDate = null;
    }

    Deadline(String task, LocalDate date) {
        super(task);
        this.byDate = date;
        this.byString = null;
    }

    public String getDate() {
        if (byString != null) {
            return byString;
        } else {
            return byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        if (byDate != null) {
            return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + byString + ")";
        }
    }
}
