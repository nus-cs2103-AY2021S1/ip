import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Deadline extends Task {
    private LocalDateTime date;

    public Deadline(String details, LocalDateTime date) {
        super(details);
        this.date = date;
    }

    public Deadline(String details, boolean done, LocalDateTime date) {
        super(details, done);
        this.date = date;
    }

    @Override
    public String store() {
        String done = this.done ? "T " : "F ";
        return "D " + done + this.details + " /by " + this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a", Locale.ENGLISH)) + ")";
    }
}
