import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Deadline extends Task implements Serializable {

    protected LocalDateTime deadlineDateTime;

    public Deadline(String deadlineName, LocalDateTime deadlineDateTime) {
        super(deadlineName);
        this.deadlineDateTime = deadlineDateTime;
    }

    public String getDeadlineDateTime() throws DateTimeParseException {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a", Locale.ENGLISH);
        return deadlineDateTime.format(outputFormat);
    }

    @Override
    public String toString() {
            return "[D]" + "[" + getStatusIcon() + "] " + getDescription() + " (by: " + getDeadlineDateTime() + ")";
    }
}