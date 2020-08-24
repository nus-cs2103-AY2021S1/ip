import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Deadline extends Task {
    protected LocalDateTime by; //deadline

    public Deadline(String description, String by) {
        super(description);
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + by.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")";
    }
}
