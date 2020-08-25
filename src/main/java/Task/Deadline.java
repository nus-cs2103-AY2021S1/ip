package Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.byDate = LocalDateTime.parse(reformatedDateTime());
        } catch (DateTimeParseException e) {
            this.byDate = null;
        }
    }

    public String toString() {
        String icon = this.completed ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]";
        if (this.byDate == null) {
            return "[D]" + icon + " " + this.description + " (by: " + this.by + ")";
        } else {
            return "[D]" + icon + " " + this.description + " (by: "
                    + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ")";
        }
    }

    private String reformatedDateTime() {
        String[] bySplit = this.by.split(" ", 2);
        String date = bySplit[0];
        String[] dateSplit = date.split("/", 3);
        String time = bySplit[1];
        String hour = time.substring(0, 2);
        String minute = time.substring(2);
        return dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0] + "T" + hour + ":" + minute + ":00";
    }
}
