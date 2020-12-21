import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task implements Serializable {

    protected String by;
    private LocalDate date;

    /**
     * Constructor for Events
     * @param description
     * @param by
     */
    public Events(String description, String by) {
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
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at:" + by + ")";
    }
}
