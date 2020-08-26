import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() { return at;}

    public String getTime() {
        try {
            Date d1 = new SimpleDateFormat("dd/mm/yyyy HHmm").parse(at);
            return new SimpleDateFormat("HH:mm, dd MMM yyyy").format(d1);
        } catch (ParseException e) {
            System.out.println("Invalid format");
        }
        return "";
    }

    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTime() + ")";
    }
}