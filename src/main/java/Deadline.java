import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task implements Serializable {

    protected String by;
    private LocalDate date;

    /**
     * Constructor for the Deadline Class
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.date = LocalDate.parse(by);
            this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.by = by;
        }
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by:" + by + ")";
    }
}
