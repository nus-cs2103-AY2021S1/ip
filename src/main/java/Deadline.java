import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTime() {
        try {
            Date d1 = new SimpleDateFormat("dd/mm/yyyy HHmm").parse(by);
            return new SimpleDateFormat("HH:mm, dd MMM yyyy").format(d1);
        } catch (ParseException e) {
            System.out.println("Invalid format");
        }
        return "";
    }

    public String getType() {
        return "D";
    }

    public String getBy() { return by; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getTime() + ")";
    }
}