import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;
    protected LocalDateTime byDateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.setByDateAndTime();
    }

    private void setByDateAndTime() {
        try {
            this.byDateTime = LocalDateTime.parse(this.by);
            this.byDate = this.byDateTime.toLocalDate();
        } catch (DateTimeParseException e) {
            try {
                this.byDate = LocalDate.parse(this.by);
            } catch (DateTimeParseException ignored) { }
        }
    }

    private String getFormattedBy() {
        if (this.byDateTime != null) {
            return this.byDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        } else if (this.byDate != null) {
            return this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return this.by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedBy() + ")";
    }
}